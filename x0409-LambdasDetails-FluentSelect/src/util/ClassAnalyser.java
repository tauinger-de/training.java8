package util;

// refactoring required.....


import org.objectweb.asm.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ClassAnalyser extends ClassVisitor implements Opcodes {

    private static Map<String, LambdaExpression> entries = new HashMap<>();

    private static Map<String, Class<?>> primitiveTypes = new HashMap<>();

    static {
        primitiveTypes.put("I", int.class);
    }

    private static Class<?> descToClass(String desc) {
        try {
            Class<?> cls = primitiveTypes.get(desc);
            if (cls == null) {
                Type fieldType = Type.getType(desc);
                cls = Class.forName(fieldType.getClassName());
            }
            return cls;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ClassAnalyser() {
        super(ASM5);
    }

    @Override
    public MethodVisitor visitMethod(int access, String methodName, String desc, String signature, String[] exceptions) {
        if (!methodName.startsWith("lambda"))
            return null;
        return new MethodVisitor(ASM5) {

            boolean relevant = false;

            Class<?> ownerClass;
            Class<?> fieldClass;
            String fieldName;
            Op op;
            Object value;

            @Override
            public void visitJumpInsn(int opcode, Label label) {
                if (!relevant)
                    return;
                if (opcode == IF_ICMPNE || opcode == IF_ACMPNE)
                    this.op = Op.EQ;
                else if (opcode == IF_ICMPEQ || opcode == IF_ACMPEQ)
                    this.op = Op.NE;
                else if (opcode == IF_ICMPNE || opcode == IF_ACMPNE)
                    this.op = Op.EQ;
                else if (opcode == IF_ICMPGE)
                    this.op = Op.LE;
                else if (opcode == IF_ICMPLE)
                    this.op = Op.GE;
                else if (opcode == IF_ICMPLT)
                    this.op = Op.GT;
                else if (opcode == IF_ICMPGT)
                    this.op = Op.LT;
                else if (opcode == IFEQ) {
                    this.op = Op.NE;
                    this.value = 0;
                } else if (opcode == IFNE) {
                    this.op = Op.EQ;
                    this.value = 0;
                } else if (opcode == IFLE) {
                    this.op = Op.GT;
                    this.value = 0;
                } else if (opcode == IFGE) {
                    this.op = Op.LT;
                    this.value = 0;
                } else if (opcode == IFLT) {
                    this.op = Op.GE;
                    this.value = 0;
                } else if (opcode == IFGT) {
                    this.op = Op.LE;
                    this.value = 0;
                }
            }

            @Override
            public void visitIntInsn(int opcode, int operand) {
                if (!relevant)
                    return;
                this.value = operand;
            }

            @Override
            public void visitLdcInsn(Object obj) {
                if (!relevant)
                    return;
                this.value = obj;
            }

            @Override
            public void visitFieldInsn(int opc, String owner, String fieldName, String desc) {
                if (opc != GETFIELD)
                    return;
                this.relevant = true;
                this.ownerClass = descToClass("L" + owner + ";");
                this.fieldClass = descToClass(desc);
                this.fieldName = fieldName;
            }

            @Override
            public void visitEnd() {
                if (!relevant)
                    return;
                final LambdaExpression entry = new LambdaExpression(this.ownerClass, this.fieldClass, this.fieldName, this.op, this.value);
                entries.put(methodName, entry);

            }
        };
    }

    public static Map<String, LambdaExpression> run(String className) {
        try {
            ClassAnalyser cp = new ClassAnalyser();
            ClassReader cr = new ClassReader(className);
            cr.accept(cp, 0);
            return Collections.unmodifiableMap(entries);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
