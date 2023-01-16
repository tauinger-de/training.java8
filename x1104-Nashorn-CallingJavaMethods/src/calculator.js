

var plus = function(x, y) {
    print('plus(' + x + ', ' + y + ')');
	var cls = Java.type('appl.Calculator');
	return cls.plus(x, y);
};

var minus = function(obj, x, y) {
    print('minus(' + obj + ', ' + x + ', ' + y + ')');
	return obj.minus(x, y);
};

var times = function(x, y) {
    print('times(' + x + ', ' + y + ')');
	var cls = Java.type('appl.Calculator');
	var obj = new cls();
	
	return obj.times(x, y);
};
