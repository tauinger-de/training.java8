package util.workviewer;

import util.workviewer.Model.Band;
import util.workviewer.Work.Viewer;

import javax.swing.*;
import java.awt.*;

import static util.Util.XRunnable.xrun;

public class GuiViewer extends JFrame implements Viewer {

    private final int sleepTime = 50;
    private final int linesPerSecond = 40;
    private final int fontSize = 16;
    private final Font font = new Font("Arial", Font.BOLD, this.fontSize);

    private volatile boolean isRunning = false;
    private volatile long startTime;
    private volatile Model model;

    @Override
    public void beginWork(String text, Color color) {
        if (!this.isRunning)
            throw new IllegalStateException("viewer is not running");
        this.model.beginWork(Thread.currentThread(), text, color);
    }

    @Override
    public void endWork() {
        if (!this.isRunning)
            throw new IllegalStateException("viewer is not running");
        this.model.endWork(Thread.currentThread());
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(this.font);
        label.setBackground(Color.lightGray);
        label.setBorder(BorderFactory.createLineBorder(Color.gray));
        label.setOpaque(true);
        return label;
    }

    private void drawString(Graphics g, String text, int y) {
        g.setColor(Color.black);
        g.setFont(this.font);
        g.drawString(text, 10, y + this.fontSize);
    }

    private void drawLine(Graphics g, int y, int w) {
        g.setColor(Color.gray);
        g.drawLine(1, y, w - 2, y);
    }

    class BandPanel extends JPanel {
        private final Model.Band band;
        private final JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                if (GuiViewer.this.model == null)
                    return;
                synchronized (GuiViewer.this.model) {
                    final Color color = this.getBackground();
//					final int elapsed = (int) (System.currentTimeMillis() - startTime);
//					final int y = elapsed * linesPerSecond / 1000;
//					for (int i = 0; i < y; i += linesPerSecond) {
//						ViewerImpl.this.drawLine(g, i, this.getWidth());
//					}
                    for (Model.Work work : BandPanel.this.band) {
                        int begin = (int) (work.getBegin() - GuiViewer.this.startTime);
                        int end = (int) ((work.getEnd() == Long.MAX_VALUE ? System.currentTimeMillis() : work.getEnd()) - GuiViewer.this.startTime);
                        final int y0 = begin * GuiViewer.this.linesPerSecond / 1000;
                        final int y1 = end * GuiViewer.this.linesPerSecond / 1000;
                        g.setColor(color);
                        g.drawLine(0, y0, this.getWidth(), y0);
                        g.setColor(work.getColor());
                        g.fillRect(1, y0 + 1, this.getWidth() - 2, y1 - y0 - 1);
                        g.setColor(color);
                        g.drawLine(0, y1, this.getWidth(), y1);
                        GuiViewer.this.drawString(g, work.getText(), y0);
                    }
                }
            }
        };

        public BandPanel(Model.Band band) {
            this.band = band;
            this.setLayout(new BorderLayout());
            final JLabel label = GuiViewer.this.createLabel("Thread " + band.getThread().getId());
            this.add(label, BorderLayout.NORTH);
            this.panel.setBorder(BorderFactory.createLineBorder(Color.gray));
            this.add(this.panel, BorderLayout.CENTER);
        }
    }

    class TimePanel extends JPanel {
        private final JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                if (GuiViewer.this.model == null)
                    return;
                synchronized (GuiViewer.this.model) {
                    final int elapsed = (int) (System.currentTimeMillis() - GuiViewer.this.startTime);
                    final int y = elapsed * GuiViewer.this.linesPerSecond / 1000;
                    g.setColor(Color.lightGray);
                    g.fillRect(1, 0, this.getWidth() - 2, y);
                    g.setFont(GuiViewer.this.font);
                    for (int i = 0, t = 0; i < y; i += GuiViewer.this.linesPerSecond, t += 1000) {
                        GuiViewer.this.drawLine(g, i, this.getWidth());
                        GuiViewer.this.drawString(g, String.valueOf(t), i);
                    }
                }
            }
        };

        public TimePanel() {
            this.setLayout(new BorderLayout());
            final JLabel label = GuiViewer.this.createLabel("Time");
            this.add(label, BorderLayout.NORTH);
            this.panel.setBorder(BorderFactory.createLineBorder(Color.gray));
            this.add(this.panel, BorderLayout.CENTER);
            this.panel.setPreferredSize(new Dimension(60, 700));
        }
    }

    private final TimePanel timePanel = new TimePanel();
    private final JPanel centerPanel = new JPanel();

    private void createModel() {
        this.model = new Model();
        this.model.addModelListener(new Model.Listener() {
            @Override
            public void bandAdded(Band band) {
                SwingUtilities.invokeLater(() -> {
                    final BandPanel bandPanel = new BandPanel(band);
                    bandPanel.doLayout();
                    GuiViewer.this.centerPanel.add(bandPanel);
                    GuiViewer.this.centerPanel.doLayout();
                    bandPanel.doLayout();
                });
            }

            @Override
            public void workBegin(Band band) {
                SwingUtilities.invokeLater(() -> GuiViewer.this.centerPanel.getComponent(band.getIndex()).repaint());
            }

            @Override
            public void workEnd(Band band) {
                SwingUtilities.invokeLater(() -> GuiViewer.this.centerPanel.getComponent(band.getIndex()).repaint());
            }
        });
    }

    private void clearCenterPanel() {
        for (int i = this.centerPanel.getComponentCount() - 1; i >= 0; i--)
            this.centerPanel.remove(i);
    }

    @Override
    public void start() {
        this.createModel();
        this.clearCenterPanel();
        this.startTime = System.currentTimeMillis();
        this.isRunning = true;
    }

    @Override
    public void stop() {
        this.isRunning = false;
    }

    @Override
    public void terminate() {
        System.exit(0);
    }

    public GuiViewer() {
        super("workviewer");
        this.setLayout(new BorderLayout());
        this.add(this.timePanel, BorderLayout.WEST);
        this.centerPanel.setLayout(new GridLayout());
        this.add(this.centerPanel, BorderLayout.CENTER);
        this.centerPanel.setPreferredSize(new Dimension(1000, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        new Thread(() -> {
            while (true) {
                xrun(() -> Thread.sleep(this.sleepTime));
                if (this.isRunning)
                    SwingUtilities.invokeLater(() -> this.repaint());
            }
        }).start();
    }

}
