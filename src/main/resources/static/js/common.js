var CONFIG = {
     'url_img':'https://dev01-trade.xyz/img/'
};


// TODO autocomplete
$(document).ready(function() {
	window.me = {
			libs: {}
	};
	me.libs.katakanaToHiragana = function(katakana) {
		var str = this.hanKata2zenKata(katakana);
		// 半角→全角
		str = str.replace(/[\u30a1-\u30f6]/g, function(match){
			const chr = match.charCodeAt(0) - 0x60;
			return String.fromCharCode(chr);
		});
		// 全角→（英語・文字）
		str = str.replace(/[A-Za-z0-9]/g, function(s) {
		    return String.fromCharCode(s.charCodeAt(0) + 65248);
		});
		str = this.utf8ciconvert(str);
		return str.toLowerCase();
	};
	
	/** 半角カタカナ→全角カタカナ* */
	me.libs.hanKata2zenKata = function( str ){
	    for(i = 0; i < this.hanKata.length; i++){
	        while(str.indexOf(this.hanKata[i]) >= 0){
	            str = str.replace(this.hanKata[i], this.zenKata[i]);
	        }
	    }
	    return str;
	}
	
	me.libs.utf8ciconvert = function( str ){
	    for(i = 0; i < this.otherKata.length; i++){
	        while(str.indexOf(this.otherKata[i]) >= 0){
	            str = str.replace(this.otherKata[i], this.otherKataUtf8[i]);
	        }
	    }
	    return str;
	}
	
	me.libs.hanKata = new Array(
	        "ｶﾞ", "ｷﾞ", "ｸﾞ", "ｹﾞ", "ｺﾞ", "ｻﾞ", "ｼﾞ", "ｽﾞ", "ｾﾞ", "ｿﾞ",
	        "ﾀﾞ", "ﾁﾞ", "ﾂﾞ", "ﾃﾞ", "ﾄﾞ", "ﾊﾞ", "ﾊﾟ", "ﾋﾞ", "ﾋﾟ", "ﾌﾞ",
	        "ﾌﾟ", "ﾍﾞ", "ﾍﾟ", "ﾎﾞ", "ﾎﾟ", "ｳﾞ", "ｧ",  "ｱ",  "ｨ",  "ｲ",
	        "ｩ",  "ｳ",  "ｪ",  "ｴ",  "ｫ",  "ｵ",  "ｶ",  "ｷ",  "ｸ",  "ｹ",
	        "ｺ",  "ｻ",  "ｼ",  "ｽ",  "ｾ",  "ｿ",  "ﾀ",  "ﾁ",  "ｯ",  "ﾂ",
	        "ﾃ",  "ﾄ",  "ﾅ",  "ﾆ",  "ﾇ",  "ﾈ",  "ﾉ",  "ﾊ",  "ﾋ",  "ﾌ",
	        "ﾍ",  "ﾎ",  "ﾏ",  "ﾐ",  "ﾑ",  "ﾒ",  "ﾓ",  "ｬ",  "ﾔ",  "ｭ",
	        "ﾕ",  "ｮ",  "ﾖ",  "ﾗ",  "ﾘ",  "ﾙ",  "ﾚ",  "ﾛ",  "ﾜ",  "ｳｨ",
	        "ｳｪ", "ｦ",  "ﾝ",  "ｰ",  "｡",  "｢",  "｣",  "､",  "･",  "ﾞ",
	        "ﾟ"
	    );
	me.libs.zenKata = new Array(
	        "ガ", "ギ", "グ", "ゲ", "ゴ", "ザ", "ジ", "ズ", "ゼ", "ゾ",
	        "ダ", "ヂ", "ヅ", "デ", "ド", "バ", "パ", "ビ", "ピ", "ブ",
	        "プ", "ベ", "ペ", "ボ", "ポ", "ヴ", "ァ", "ア", "ィ", "イ",
	        "ゥ", "ウ", "ェ", "エ", "ォ", "オ", "カ", "キ", "ク", "ケ",
	        "コ", "サ", "シ", "ス", "セ", "ソ", "タ", "チ", "ッ", "ツ",
	        "テ", "ト", "ナ", "ニ", "ヌ", "ネ", "ノ", "ハ", "ヒ", "フ",
	        "ヘ", "ホ", "マ", "ミ", "ム", "メ", "モ", "ャ", "ヤ", "ュ",
	        "ユ", "ョ", "ヨ", "ラ", "リ", "ル", "レ", "ロ", "ワ", "ヰ",
	        "ヱ", "ヲ", "ン", "ー", "。", "「", "」", "、", "・", "゛",
	        "゜"
	    );
	me.libs.otherKata = new Array(
		'が','ぎ','ぐ','げ','ご',
		'ざ','じ','ず','ぜ','ぞ',
		'だ','ぢ','づ','で',
		'ば','ぱ','び','ぴ','ぶ','ぷ','べ','ぺ','ぼ','ぽ'
	);
	me.libs.otherKataUtf8 = new Array(
		'か','き','く','げ','こ',
		'さ','し','す','せ','そ',
		'た','ち','つ','て',
		'は','は','ひ','ふ','ふ','へ','へ','ほ','ほ'
	);
	// TODO ははは→ばばば
});

jQuery(document).ready(function() {
    $("input").each(function() {
    	var currentColor;
        $(this).on('focus', function() {
        	currentColor = $(this).css('background');
            $(this).css({
                'background': '#b5d7f7'
            });
        });
        if ($(this).hasClass("SeiNotNull")) {
            $(this).on('blur', function() {
                $(this).css({
                    'background': currentColor
                });
            });
        } else {
            $(this).on('blur', function() {
                $(this).css({
                    'background': currentColor
                });
            });
        }
    });
    renderDate();
    // hung.pd
    $("#csvfile").click(function () {
        $("#upfile").trigger('click');
   });
   
 // hung.pd
    $("#file").click(function () {
        $("#upfile").trigger('click');
   });
    
 // hung.pd
    $("#upfile").change(function(){
        $("#csvfile").val(this.value);
    });
    
    // ha.dv
    $(".datepicker").each(function(k, v) {
    	$(v).datepicker({
	    	showOn: "button",
	        buttonImage: "/images/btn/calendar.png",
	        buttonImageOnly: true,
	        buttonText: "Select date",
	        dateFormat: $(v).attr('data-date-format') || 'yy/mm/dd',
	        beforeShow: function() {
	        	if($(v).is(':disabled')) {
	        		return false;
	        	}
	        }
	    });
    })

    $('#div_data tr:first-child td').each(function(index){
        dataWidth = $(this).width();
        $('#div_header tr:first-child td:eq('+index+')').attr("style","width:" + dataWidth + "px !important");
    });
    $('#div_data1 tr:first-child td').each(function(index){
        dataWidth = $(this).width();
        $('#div_header1 tr:first-child td:eq('+index+')').attr("style","width:" + dataWidth + "px !important");
    });
});

/**
 * quan ly row click cac popup
 */

function jsonParse(x) {
	return x;
}

function clousePopupHandler(elem) {
	var parent = $(elem).parents('#tau-popup');
	$(parent[0]).html('');
	$(parent[0]).addClass('hidden');
}

function evalData(data) {
	var param = eval("(function(x){return x})({data:" + data + "})");
	return param.data;
}
/**
 * search data
 * @param elem
 * @param divId
 * @returns
 */
function lostFocusShowPopup(elem, divId) {
	// get attemp data
	var attr = $(elem.target).attr('data');
	var data = evalData(attr);
	handlerPopupLostFocus(elem, divId, data);
}

/**
 * search data
 * @param elem
 * @param divId
 * @returns
 */
function lostFocusSearchPopup(elem, divId) {
	var name = $(elem.target).attr('name');
	// get attemp data
	var attr = $(elem.target).attr('data');
	var data = evalData(attr);
	
	if(isSet(name)) {
		data[name] = $(elem.target).val();
	}
	hanlerPopupShow(elem, divId, data);
}

function handlerPopupLostFocus(elem, divId, data) {
	
	$(divId).html('');
	var action = $(elem.target).attr('action');
	
	// TODO FIXCODE
	if(isUnset(action)) {
		window.alert('作成中')
		return;
	}
	var attrNm = $(elem.target).attr('name');
	
	if(isSet(data)) {
		data[attrNm] = $(elem.target).val();
	}
	// get pre search function
	var preSearch = $(elem.target).attr('pre-search');
	// get af search function
	var afSearch = $(elem.target).attr('af-search');
	// get rowclick function
	var rowClick = $(elem.target).attr('row-click');
	
	appendCallbackPopup($(divId), afSearch, rowClick);
	var targetData = $(elem.target).attr('target');
	if(isSet(targetData)) {
		$(divId).attr('target', targetData);
	}
	
	// neu truyen vao binding data
	if(isFunction(window[preSearch])) {
		window[preSearch]($(elem).attr('id'), data);
	} else {
		loadDataForPopup($(elem).attr('id'), data);
	}
	// call ajax
	api.getAjaxPopUp(action, data, function(responseData) {
		var elem = $(responseData);
		$(divId).html('');
		$(divId).append(elem);
		$(divId).removeClass('hidden');
		var afSearch = $(divId).attr('af-search');
		scrollDiv();
		// neu truyen vao binding data
		if(isFunction(window[afSearch])) {
			window[afSearch]($(elem).attr('id'), data);
		}
		setFocusPopup(divId);
	});
}

//handler when click to 参考ボタン
function hanlerPopupShow(elem, divId, data) {
	
	$('#' + divId).html('');
	var action = $(elem.target).attr('action');
	// TODO FIXCODE
	if(isUnset(action)) {
		window.alert('作成中')
		return;
	}
	var preSearch = $(elem.target).attr('pre-search');
	var afSearch = $(elem.target).attr('af-search');
	var rowClick = $(elem.target).attr('row-click');
	var targetData = $(elem.target).attr('target');
	// neu truyen vao binding data
	if(isFunction(window[preSearch])) {
		window[preSearch](divId, data);
	} else {
		loadDataForPopup(divId, data);
	}
	
	if(isSet(targetData)) {
		$('#' + divId).attr('target', targetData);
	}
	// add event for row click
	appendCallbackPopup($('#' + divId), afSearch, rowClick);
	// call ajax
	api.postAjaxPopup(action, data, function(data) {
		var elem = $(data);
		var totalCount = $(elem).attr('total-count');
		var target = $('#' + divId).attr('target');
		if(totalCount == '1') {
			var tr = $(elem).find('.tblSearchResultBody').find('tbody').find('tr')[0];
			var tds = $(tr).find('td');
			var result = getDataOfRow(tr);
			if(isFunction(window[rowClick])) {
				window[rowClick](result, target);
			}
		} else if(totalCount > 1) {
			$('#' + divId).append(elem);
			$('#' + divId).removeClass('hidden');
			setFocusPopup(divId);
			scrollDiv();
			$(elem).find('.tblSearchResultBody').find('tbody').find('tr').click(function(e) {
				var parent = $(this).parents('#tau-popup')[0];
				var currentElem = getTarget(e.target);
				var result = getDataOfRow(currentElem);
				$(parent).addClass('hidden');
				var tblRowClick = $('#' + divId).attr('row-click');
				if(isFunction(window[tblRowClick])) {
    				window[tblRowClick](result, target);
    			}
			});
			var afSearch = $('#' + divId).attr('af-search')
			if(isFunction(window[afSearch])) {
				window[afSearch]();
			}
		} else {
			if(isFunction(window[rowClick])) {
				window[rowClick]({}, target);
			}
		}
	});
}
function sei_menu_trans() {
    var Ans = confirm('ページを切り替えると変更された内容が無効になりますがよろしいですか？');
    if (Ans == true) {
        g_seiAcceptEditWarn = true;
        return true;
    } else
        return false;
}
var size = [1212, 726];


function loginPage(p) {
    open(location, '_self').close();;
   
   var myWindow =  window.open(p, "session", "directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no", "width="
		   + size[0] + ",height=" + size[1]);
   myWindow.resizeTo(size[0], size[1]);
   myWindow.focus();
}

function submitAction(action) {
    $("#redirect").attr('action', action).submit();
}

// ---------------------------------------------
function confirmWhenCUD() {
    return confirm('登録をおこないます。よろしいですか？');
}
function confirmWhenUpdate() {
    return confirm('更新をおこないます。よろしいですか？');
}
function confirmWhenDelete() {
    return confirm('消除をおこないます。よろしいですか？');
}
function SeiFormName(x_form) {
    if (!x_form.name.type)
        return x_form.name;
    var p_attr = x_form.attributes;
    var p_attrlen = p_attr.length;
    for (var i = 0; i < p_attrlen; i++) {
        if (p_attr[i].nodeName == 'name')
            return p_attr[i].nodeValue
    }
    return x_form.pg.value;
}

function SeiFocusField(x_element) {
    if (x_element == null || x_element.type == 'hidden' ||
        x_element.className == 'SeiNoEntry' ||
        x_element.className == 'SeiArrayCheck' ||
        x_element.className == 'PmsColorField') {
        return false;
    }
    if (x_element.tabIndex == -1) {
        return false;
    }
    if (typeof(x_element.tagName) == 'string' && x_element.tagName == 'DIV') {
        x_element.focus();
        return true;
    }
    if (x_element.className == 'SeiRadio2') {
        SeiFocusIfVisible(x_element);
        return true;
    }
    if (x_element.type == 'checkbox' || x_element.type == 'radio') {
        x_element = x_element.form.elements[x_element.name];
    }
    if (x_element.options) {
        if (event)
            event.cancelBubble = true;
        return SeiFocusIfVisible(x_element);
    } else if (x_element.length) {
        var p_pos;
        for (p_pos = 0; p_pos < x_element.length; p_pos++) {
            if (x_element[p_pos].checked) {
                if (event)
                    event.cancelBubble = true;
                return SeiFocusIfVisible(x_element[p_pos]);
            }
        }
        if (event)
            event.cancelBubble = true;
        return SeiFocusIfVisible(x_element[0]);
    } else if (x_element.name.indexOf('chkall_') == 0) {
        return false;
    } else {
        if (event)
            event.cancelBubble = true;
        return SeiFocusIfVisible(x_element);
    }
}

function SeiFocusField2(x_element, x_current) {
    if (!SeiFocusFieldSub2(x_element)) {
        if (!x_element) {
            if (x_current)
                return SeiFocusNext(x_current, false);
            return false;
        }
        var p_elem = x_element.form.elements,
            p_length = p_elem.length;
        for (var i = 0; i < p_length; i++) {
            if (p_elem[i].type == 'hidden' || p_elem[i].type == 'submit' ||
                p_elem[i].type == 'reset')
                continue;
            if (p_elem[i].readOnly || p_elem[i].className == 'SeiNoEntry')
                continue;
            if (SeiFocusFieldSub2(p_elem[i]))
                return true
        }
        return false;
    }
    return true;
}

function SeiFocusFieldSub2(x_element) {
    if (x_element == null || x_element.type == 'hidden') {
        return false;
    }
    if (x_element.className == 'SeiRadio2') {
        SeiFocusIfVisible(x_element);
        return true;
    }
    if (x_element.type == 'checkbox' || x_element.type == 'radio') {
        x_element = x_element.form.elements[x_element.name];
    }
    if (x_element.options) {
        if (event)
            event.cancelBubble = true;
        return SeiFocusIfVisible(x_element);
    } else if (x_element.length) {
        var p_pos;
        for (p_pos = 0; p_pos < x_element.length; p_pos++) {
            if (x_element[p_pos].checked) {
                if (event)
                    event.cancelBubble = true;
                return SeiFocusIfVisible(x_element[p_pos]);
            }
        }
        if (event)
            event.cancelBubble = true;
        return SeiFocusIfVisible(x_element[0]);
    } else if (x_element.name.indexOf('chkall_') == 0) {
        return false;
    } else {
        if (event)
            event.cancelBubble = true;
        return SeiFocusIfVisible(x_element);
    }
}

function SeiFocusIfVisible(x_element) {
    if (x_element && x_element.name && x_element.parentNode &&
        x_element.parentNode.className == 'SeiTimeSelItem') {
        var p_idx = x_element.name.indexOf('pms_mi_');
        if (p_idx >= 0) {
            var p_hh = document.all(x_element.name.substring(0, p_idx) +
                'pms_hh_' + x_element.name.substring(p_idx + 7));
            x_element = p_hh;
        }
    }
    var p_element = x_element;
    while (p_element) {
        if (!p_element.style || p_element.style.visibility == 'hidden' ||
            p_element.style.display == 'none' || p_element.disabled ||
            p_element.type == 'hidden') {
            return false;
        }
        p_element = p_element.parentElement;
    }
    if (typeof document.activeElement != 'unknown' && document.activeElement &&
        document.activeElement.tagName == 'SELECT')
        document.activeElement.blur();
    x_element.focus();
    return true;
}

function SeiFocusNext(x_element, x_formnext) {
    if (!x_element.form)
        return false;
    var i, j, p_func;
    var p_elements = x_element.form.elements;
    var p_length = p_elements.length;
    if (x_element.tabIndex && x_element.tabIndex > 0) {
        var p_nextTabIndex = -1,
            p_nextTabElem = null;
        for (i = 0; i < p_length; i++) {
            if (p_elements[i] != x_element)
                continue;
            for (j = i + 1; j < p_length; j++) {
                if (p_elements[j].name != x_element.name &&
                    p_elements[j].tabIndex == x_element.tabIndex) {
                    p_nextTabElem = p_elements[j];
                    break;
                }
            }
            if (p_nextTabElem)
                break;
            for (j = 0; j < p_length; j++) {
                if (j == i || !p_elements[j].tabIndex ||
                    p_elements[j].tabIndex <= x_element.tabIndex)
                    continue;
                if (p_nextTabIndex < 0 ||
                    p_elements[j].tabIndex < p_nextTabIndex) {
                    p_nextTabElem = p_elements[j];
                    p_nextTabIndex = p_elements[j].tabIndex;
                }
            }
            if (p_nextTabElem)
                break;
            for (j = i + 1; j < p_length; j++) {
                if (p_elements[j].tabIndex > 0 ||
                    p_elements[j].type == 'hidden')
                    continue;
                if (SeiFocusField(p_elements[j]))
                    return true;
            }
            break;
        }
        if (p_nextTabElem && SeiFocusField(p_nextTabElem))
            return true;
    }
    p_func = 'sei' + SeiFormName(x_element.form) + 'FocusNext';
    x_formnext = (typeof x_formnext == 'undefined' || x_formnext);
    if (x_formnext && eval('typeof(' + p_func + ')') == 'function') {
        var p_fieldname = x_element.name;
        if (x_element.type == 'radio' || x_element.type == 'checkbox') {
            if (x_element.form.elements[x_element.name].length > 0) {
                p_fieldname += '[0]';
            }
        }
        if (eval(p_func + '(\'' + p_fieldname + '\')')) {
            return true;
        }
    }
    for (i = 0; i < p_length; i++) {
        if (p_elements[i] != x_element) {
            continue;
        }
        for (j = i + 1; j < p_elements.length; j++) {
            if (p_elements[j].name == x_element.name) {
                continue;
            }
            var p_parent = p_elements[j].parentNode;
            if (p_parent && p_parent.className == 'SeiTimeSelItem' &&
                x_element.name) {
                var p_idx = p_elements[j].name.indexOf('pms_mi_');
                if (p_idx >= 0) {
                    continue;
                }
            }
            if (!SeiFocusField(p_elements[j])) {
                continue;
            }
            if (!x_formnext)
                return p_elements[j].name != x_element.name;
            return false;
        }
        for (j = 0; j < i; j++) {
            if (p_elements[j].name == x_element.name ||
                !SeiFocusField(p_elements[j])) {
                continue;
            }
            if (!x_formnext)
                return p_elements[j].name != x_element.name;
            return false;
        }
    }
    return true;
}

function SeiFocusNextLineTop(x_field, x_arrayno, x_prenm, x_vertical) {
    if (!x_field.form)
        return false;
    var p_elements = x_field.form.elements,
        p_len = x_field.form.elements.length,
        p_pos = -1;
    if (x_vertical) {
        var p_suffix = '_' + (x_arrayno + 1);
        for (var i = 0; i < p_len; i++) {
            if (SeiStringStartsWith(p_elements[i].name, x_prenm) &&
                SeiStringEndsWith(p_elements[i].name, p_suffix)) {
                if (SeiFocusField(p_elements[i]))
                    return true;
            }
        }
    }
    for (var i = 0; i < p_len; i++) {
        if (p_elements[i] == x_field) {
            p_pos = i;
            break;
        }
    }
    var p_suffix = '_' + x_arrayno;
    for (var i = p_pos + 1; i < p_len; i++) {
        if (!SeiStringEndsWith(p_elements[i].name, p_suffix)) {
            if (SeiFocusField(p_elements[i]))
                return true;
        }
    }
    return false;
}

function SeiFocusPrevLineTop(x_field, x_arrayno, x_prenm, x_vertical) {
    if (!x_field.form)
        return false;
    var p_elements = x_field.form.elements,
        p_len = x_field.form.elements.length,
        p_pos = -1;
    if (x_arrayno > 0 && x_vertical) {
        var p_suffix = '_' + (x_arrayno - 1);
        for (var i = 0; i < p_len; i++) {
            if (SeiStringStartsWith(p_elements[i].name, x_prenm) &&
                SeiStringEndsWith(p_elements[i].name, p_suffix)) {
                if (SeiFocusField(p_elements[i]))
                    return true;
            }
        }
        return false;
    }
    for (var i = 0; i < p_len; i++) {
        if (p_elements[i] == x_field) {
            p_pos = i;
            break;
        }
    }
    var p_suffix = '_' + x_arrayno;
    for (var i = p_pos - 1; i >= 0; i--) {
        if (!SeiStringEndsWith(p_elements[i].name, p_suffix)) {
            p_pos = i;
            break;
        }
    }
    if (x_arrayno == 0) {
        for (var i = p_pos - 1; i >= 0; i--) {
            if (SeiFocusField(p_elements[i]))
                return true;
        }
    } else {
        p_suffix = '_' + (x_arrayno - 1);
        for (var i = p_pos; i >= 0; i--) {
            if (i == 0 || !SeiStringEndsWith(p_elements[i - 1].name, p_suffix) ||
                p_elements[i - 1].className == 'SeiArrayCheck') {
                if (SeiFocusField(p_elements[i]))
                    return true;
            }
        }
    }
    return false;
}

function SeiDisableField(x_element) {
    if (x_element) {
        x_element.onkeydown = function() {
            return false;
        }
        x_element.onblur = function() {
            return false;
        }
        x_element.onchange = function() {
            return false;
        }

        var p_img = document.getElementById(x_element.name + '-refbtn');
        if (p_img != null)
            p_img.parentNode.onclick = function() {
                return false;
            }
        p_img = document.getElementById(x_element.name + '-clearbtn');
        if (p_img != null)
            p_img.parentNode.onclick = function() {
                return false;
            }

    }
}

function SeiStringStartsWith(x_str, x_prefix) {
    if (x_str.length < x_prefix.length)
        return false;
    for (var i = 0; i < x_prefix.length; i++) {
        if (x_str.charAt(i) != x_prefix.charAt(i))
            return false;
    }
    return true;
}

function SeiStringEndsWith(x_str, x_suffix) {
    if (x_str.length < x_suffix.length)
        return false;
    var p_pos = x_str.length - x_suffix.length;
    for (var i = 0; i < x_suffix.length; i++) {
        if (x_str.charAt(p_pos + i) != x_suffix.charAt(i))
            return false;
    }
    return true;
}

function SeiRemoveDelim(x_str, x_delim) {
    while (x_str.indexOf(x_delim) >= 0) {
        x_str = x_str.replace(x_delim, '');
    }
    return x_str;
}

function SeiFormatDelim(x_value, x_format, x_delim) {
    if (!x_value) {
        return x_value;
    }
    var p_orgvalue = SeiRemoveDelim(x_value, x_delim);
    var i, j, p_value = '';
    for (i = 0, j = 0; i < x_format.length; i++) {
        if (j >= p_orgvalue.length)
            break;
        if (i != 0)
            p_value += x_delim;
        p_value += p_orgvalue.substring(j, j + x_format[i]);
        j += x_format[i];
    }
    return p_value + p_orgvalue.substring(j);
}

function SeiEscape(x_str) {
    if (!x_str) {
        return x_str;
    }
    var p_len = x_str.length,
        p_pos = 0,
        p_ret = null,
        p_ch = 0;
    for (var i = 0; i < x_str.length; i++) {
        if (0x80 <= x_str.charCodeAt(i) && x_str.charCodeAt(i) < 0x100) {
            p_ch = '%u00' + x_str.charCodeAt(i).toString(16);
        } else
            switch (x_str.charAt(i)) {
                case '+':
                    p_ch = '%2B';
                    break;
                case '?':
                    p_ch = '%3F';
                    break;
            }
        if (p_ch == 0)
            continue;
        if (p_ret == null) {
            p_ret = escape(x_str.substring(0, i)) + p_ch;
        } else {
            p_ret = p_ret + escape(x_str.substring(p_pos, i)) + p_ch;
        }
        p_pos = i + 1;
        p_ch = 0;
    }
    if (p_ret != null) {
        p_ret = p_ret + escape(x_str.substring(p_pos));
    }
    return p_ret || escape(x_str);
}

function SeiGetValue(x_field) {
    if (x_field == null) {
        return;
    }
    if (x_field.options) {
        if (x_field.selectedIndex < 0)
            return '';
        if (x_field.multiple) {
            var i, p_ret = '';
            for (i = 0; i < x_field.options.length; i++) {
                if (x_field.options[i].selected) {
                    if (p_ret != '')
                        p_ret += ',';
                    p_ret += x_field.options[i].value;
                }
            }
            return p_ret;
        } else {
            return x_field.options[x_field.selectedIndex].value;
        }
    }
    if (x_field.length) {
        var i, p_ret = '';
        for (i = 0; i < x_field.length; i++) {
            if (x_field[i].checked) {
                if (p_ret != '')
                    p_ret += ',';
                p_ret += x_field[i].value;
            }
        }
        return p_ret;
    }
    if (typeof x_field.type == 'string' &&
        (x_field.type.toLowerCase() == 'checkbox' || x_field.type
            .toLowerCase() == 'radio')) {
        return x_field.checked ? x_field.value : '';
    }
    if (x_field.className == 'RakHtmlEditor') {
        var p_elem = x_field.ownerDocument.getElementById(x_field.name +
            '_edit_edit');
        if (p_elem && p_elem.tagName == 'DIV') {
            return p_elem.innerHTML;
        }
    }
    return x_field.value;
}

function SeiSetValueCB(x_field, x_value) {
    if (x_field == null) {
        return;
    }
    if (x_field.type == 'hidden') {
        x_field.value = x_value;
        return;
    }
    var p_values = x_value.split(',');
    if (x_field.length == 1) {
        x_field.checked = false;
        for (var j = 0; j < p_values.length; j++) {
            if (x_field.value == p_values[j]) {
                x_field.checked = true;
                break;
            }
        }
        return;
    }
    if (x_field.length) {
        for (var i = 0; i < x_field.length; i++) {
            x_field[i].checked = false;
            for (var j = 0; j < p_values.length; j++) {
                if (x_field[i].value == p_values[j]) {
                    x_field[i].checked = true;
                    break;
                }
            }
        }
        return;
    }
    if ((x_field.type == 'checkbox' || x_field.type == 'radio') &&
        x_field.value == x_value) {
        x_field.checked = true;
    } else {
        x_field.checked = false;
    }
}

function SeiSetValueSEL(x_field, x_value) {
    if (x_field == null) {
        return;
    }
    if (x_field.type == 'hidden') {
        x_field.value = x_value;
        return;
    }
    if (!x_field.options) {
        return SeiSetValueCB(x_field, x_value);
    }
    var p_values = x_value.split(',');
    if (x_field.multiple) {
        for (var i = 0; i < x_field.options.length; i++) {
            x_field.options[i].selected = false;
            for (var j = 0; j < p_values.length; j++) {
                if (x_field.options[i].value == p_values[j]) {
                    x_field.options[i].selected = true;
                    break;
                }
            }
        }
    } else {
        var p_selidx = -1;
        for (var i = 0; i < x_field.options.length; i++) {
            for (var j = 0; j < p_values.length; j++) {
                if (x_field.options[i].value == p_values[j]) {
                    p_selidx = i;
                    break;
                }
            }
        }
        x_field.selectedIndex = p_selidx;
    }
}

function SeiSetValueTA(x_field, x_value, x_prenm) {
    if (x_field == null) {
        return;
    }
    if (x_field.type != 'hidden') {
        return;
    }
    if (x_field.className != 'SeiTimeSel') {
        x_field.value = x_value;
        return;
    }
    var p_idx = -1;
    if (x_prenm && x_field.name) {
        p_idx = x_field.name.indexOf(x_prenm)
    }
    var p_hh;
    var p_mi;
    if (p_idx >= 0) {
        var p_first = x_field.name.substring(0, p_idx + x_prenm.length);
        var p_last = x_field.name.substring(p_idx + x_prenm.length);
        p_hh = document.getElementsByName(p_first + 'pms_hh_' + p_last);
        p_mi = document.getElementsByName(p_first + 'pms_mi_' + p_last);
    } else {
        p_hh = document.getElementsByName('pms_hh_' + x_field.name);
        p_mi = document.getElementsByName('pms_mi_' + x_field.name);
    }
    if (p_hh && p_hh[0] && p_mi && p_mi[0]) {
        var p_val = null;
        if (!isNaN(SeiParseInt(x_value))) {
            var p_hhmaxlen = p_hh[0].options[p_hh[0].options.length - 1].value.length;
            var p_mimaxlen = p_mi[0].options[p_mi[0].options.length - 1].value.length;
            var p_hhval = SeiPadding(SeiParseInt(x_value / 3600), p_hhmaxlen, 0);
            var p_mival = SeiPadding(SeiParseInt(x_value % 3600 / 60),
                p_mimaxlen, 0);
            p_val = new Array(p_hhval, p_mival);
        } else {
            p_val = x_value.split(":");
        }
        if (p_val && p_val.length >= 2) {
            SeiSetValueSEL(p_hh[0], p_val[0]);
            SeiSetValueSEL(p_mi[0], p_val[1]);
        } else {
            p_hh[0].selectedIndex = 0;
            p_mi[0].selectedIndex = 0;
        }
        if (!isNaN(SeiParseInt(p_hh[0].options[p_hh[0].selectedIndex].value)) &&
            !isNaN(SeiParseInt(p_mi[0].options[p_mi[0].selectedIndex].value))) {
            x_field.value = p_hh[0].options[p_hh[0].selectedIndex].value + ':' +
                p_mi[0].options[p_mi[0].selectedIndex].value;
        } else {
            x_field.value = '';
        }
    }
}

function SeiSetValueRT(x_field, x_value, x_prenm) {
    if (x_field == null) {
        return;
    }
    var p_elem = x_field.ownerDocument.getElementById(x_field.name +
        '_edit_edit');
    if (p_elem && p_elem.tagName == 'DIV') {
        p_elem.innerHTML = x_value;
    }
}

function SeiSetValue(x_field, x_value, x_prenm) {
    if (!x_field)
        return false;
    if (x_field.options || x_field.tagName &&
        x_field.tagName.toUpperCase() == 'SELECT') {
        return SeiSetValueSEL(x_field, x_value);
    }
    if (x_field.length && x_field.length >= 2) {
        if (x_field[0].type == 'radio' || x_field[0].type == 'checkbox') {
            return SeiSetValueCB(x_field, x_value);
        }
    }
    if (x_field.type == 'radio' || x_field.type == 'checkbox') {
        return SeiSetValueCB(x_field, x_value);
    }
    if (x_field.className == 'SeiTimeSel') {
        return SeiSetValueTA(x_field, x_value, x_prenm)
    }
    if (x_field.className == 'RakHtmlEditor') {
        return SeiSetValueRT(x_field, x_value, x_prenm)
    }
    x_field.value = x_value;
    return true;
}

function SeiClearField(x_field) {
    if (x_field == null) {
        return false;
    }
    if (x_field.options) {
        if (x_field.multiple) {
            var i;
            for (i = 0; i < x_field.options.length; i++) {
                x_field.options[i].selected = false;
            }
        } else {
            x_field.selectedIndex = 0;
        }
        return true;
    }
    if (x_field.length) {
        var i;
        for (i = 0; i < x_field.length; i++) {
            x_field[i].checked = false;
        }
        return true;
    }
    if (x_field.type == 'checkbox' || x_field.type == 'radio') {
        x_field.checked = false;
        return true;
    }
    x_field.value = '';
}

function SeiClearField2(x_form, x_field, x_pre) {
    var p_arycount = 0;
    if (x_form[x_pre + 'arraycount']) {
        p_arycount = parseInt(x_form[x_pre + 'arraycount'].value);
    }
    for (var i = 0; i < p_arycount; i++) {
        if (x_form[x_pre + x_field + '_' + i]) {
            SeiClearField(x_form[x_pre + x_field + '_' + i]);
        }
    }
}

function SeiTrim(x_value) {
    if (!x_value || !x_value.length || x_value.length == 0) {
        return x_value;
    }
    var p_start = 0,
        p_end = x_value.length;
    while (p_start < p_end) {
        if (x_value.charAt(p_start) != ' ' && x_value.charAt(p_start) != '　') {
            break;
        }
        p_start++;
    }
    while (p_end > 0) {
        if (x_value.charAt(p_end - 1) != ' ' &&
            x_value.charAt(p_end - 1) != '　') {
            break;
        }
        p_end--;
    }
    if (p_start >= p_end) {
        return x_value;
    }
    return x_value.substring(p_start, p_end);
}

function SeiRemoveComma(x_value) {
    if (!x_value || x_value == '') {
        return x_value;
    }
    var i, p_value = '' + x_value,
        p_value2 = '',
        p_dec_c = 0;
    for (i = 0; i < p_value.length; i++) {
        var p_ch = p_value.charAt(i);
        if (p_ch == '+' || p_ch == '-') {
            if (i != 0)
                return x_value;
            continue;
        }
        if (p_ch != ' ' && (p_ch < '0' || p_ch > '9') && p_ch != ',') {
            if (p_ch == '.' && p_dec_c++ == 0) {
                continue;
            }
            return x_value;
        }
    }
    var p_idx = -1,
        p_lastidx = -1;
    while ((p_idx = p_value.indexOf(',', p_idx + 1)) >= 0) {
        p_value2 += p_value.substring(p_lastidx + 1, p_idx);
        p_lastidx = p_idx;
    }
    if (p_lastidx >= 0) {
        p_value = p_value2 + p_value.substring(p_lastidx + 1);
    }
    return p_value;
}

function SeiEditComma(x_value) {
    if (typeof(x_value) == 'number')
        x_value = x_value.toString();
    if (!x_value)
        return '';
    var i, p_value = SeiTrim('' + x_value),
        p_value2 = '',
        p_dec_c = 0;
    p_value = p_value.replace(/　/g, '');
    for (i = 0; i < p_value.length; i++) {
        var p_ch = p_value.charAt(i);
        if (p_ch == '+' || p_ch == '-') {
            if (i != 0)
                return x_value;
            continue;
        }
        if (p_ch != ' ' && (p_ch < '0' || p_ch > '9') && p_ch != ',') {
            if (p_ch == '.' && p_dec_c++ == 0) {
                continue;
            }
            return x_value;
        }
    }
    p_value = SeiTrimZero(p_value);
    var p_idx = -1,
        p_lastidx = -1;
    while ((p_idx = p_value.indexOf(',', p_idx + 1)) >= 0) {
        p_value2 += p_value.substring(p_lastidx + 1, p_idx);
        p_lastidx = p_idx;
    }
    if (p_lastidx >= 0) {
        p_value = p_value2 + p_value.substring(p_lastidx + 1);
    }
    var p_sign = '';
    if ((p_idx = p_value.indexOf('-')) >= 0) {
        p_sign += p_value.substring(0, p_idx + 1);
        p_value = SeiTrim(p_value.substring(p_idx + 1));
    }
    if ((p_idx = p_value.indexOf('+')) >= 0) {
        p_sign += p_value.substring(0, p_idx + 1);
        p_value = SeiTrim(p_value.substring(p_idx + 1));
    }
    for (i = 0; i < p_value.length; i++) {
        if (p_value.charAt(i) != '0') {
            if (i == 0 || i == 1 && p_value.charAt(i) == '.') {
                break;
            }
            p_sign += p_value.substring(0, i);
            p_value = p_value.substring(i);
            break;
        }
    }
    p_idx = p_value.indexOf('.');
    if (p_idx < 0)
        p_idx = p_value.length;
    p_value2 = p_value.substring(0, p_idx % 3);
    for (i = p_idx % 3; i < p_idx; i += 3) {
        if (i != 0)
            p_value2 += ',';
        p_value2 += p_value.substring(i, i + 3);
    }
    return p_sign + p_value2 + p_value.substring(p_idx);
}

function SeiTrimZero(x_value) {
    x_value = SeiTrim(x_value);
    if (!x_value || !x_value.length || x_value.length == 0) {
        return x_value;
    }
    var p_pos = 0,
        p_value2 = null;
    while (true) {
        var p_pos2 = x_value.indexOf(' ', p_pos);
        if (p_pos2 < 0) {
            if (p_value2 != null) {
                x_value = p_value2 + x_value.substring(p_pos);
            }
            break;
        }
        if (p_value2 == null)
            p_value2 = '';
        p_value2 += x_value.substring(p_pos, p_pos2);
        p_pos = p_pos2 + 1;
    }
    var p_sign = 0;
    if (x_value.charAt(0) == '-' || x_value.charAt(0) == '+') {
        p_sign = 1;
    }
    var p_start = p_sign;
    while (p_start < x_value.length) {
        var p_ch = x_value.charAt(p_start);
        if (p_ch != '0') {
            break;
        }
        p_start++;
    }
    if (p_start >= x_value.length) {
        return x_value.charAt(x_value.length - 1);
    }
    var p_padding = '';
    if (x_value.charAt(x_value.length - 1) == '.') {
        if (x_value.length > p_sign + 1) {
            x_value = x_value.substring(0, x_value.length - 1);
        }
        if (p_start > p_sign && p_start == x_value.length) {
            p_start--;
        }
        if (p_start + p_sign == x_value.length &&
            (x_value.charAt(p_start) == '0')) {
            p_sign = 0;
        }
    } else if (x_value.charAt(p_start) == '.') {
        if (p_start == p_sign) {
            p_padding = '0';
        } else if (p_start > p_sign) {
            p_start--;
        }
    }
    return x_value.substring(0, p_sign) + p_padding +
        x_value.substring(p_start, x_value.length);
}

function SeiParseInt(x_value) {
    if (typeof(x_value) == 'number')
        return parseInt(x_value.toString());
    x_value = SeiRemoveComma(SeiTrim(x_value));
    if (x_value == '' || x_value == null)
        return NaN;
    if (x_value.indexOf('.') >= 0)
        return NaN;
    if (isNaN(Number(x_value)))
        return NaN;
    return parseInt(x_value, 10);
}

function SeiParseNum(x_value) {
    if (typeof x_value == 'number')
        return x_value;
    x_value = SeiTrim(x_value);
    if (x_value == '' || x_value == null)
        return NaN;
    x_value = SeiRemoveComma(x_value);
    x_value = x_value.replace('.', '.');
    return Number(x_value);
}

function SeiRound(x_value, x_scale) {
    if (x_scale < 0) {
        return x_value;
    }
    var p_value = new Number(SeiRemoveComma(x_value));
    if (!p_value || p_value == NaN) {
        return x_value;
    }
    return p_value.toFixed(x_scale);
}

function SeiCalcSum(x_form, x_prenm, x_field, x_sum) {
    var i, p_len = new Number(x_form.elements[x_prenm + 'arraycount'].value);
    var p_sum = 0,
        p_count = 0,
        p_min = null,
        p_max = null;
    for (i = 0; i < p_len; i++) {
        var p_num = x_form.elements[x_prenm + x_field + '_' + i].value;
        if (p_num.length == 0)
            continue;
        p_num = new Number(SeiRemoveComma(p_num));
        if (!isNaN(p_num)) {
            p_sum += p_num;
            p_count++;
            if (p_min == null || p_num > p_min)
                p_min = p_num;
            if (p_max == null || p_num < p_max)
                p_max = p_num;
        }
    }
    switch (x_sum) {
        case 'SUM':
            return p_sum;
        case 'MIN':
            return p_min;
        case 'MAX':
            return p_max;
        case 'AVG':
            return (p_count == 0 ? '' : p_sum / p_count);
        case 'COUNT':
            return p_count;
    }
    return null;
}

function SeiIndexOf(x_elements, x_check) {
    if (x_elements.length) {
        var i;
        for (i = 0; i < x_elements.length; i++) {
            if (x_elements[i] == x_check) {
                return i;
            }
        }
    }
    return -1;
}

function SeiPadding(x_value, x_len, x_pad) {
    if (x_value == null)
        x_value = '';
    if (typeof x_value != 'string')
        x_value = '' + x_value;
    if (typeof x_pad == 'undefined')
        x_pad = ' ';
    for (var i = x_value.length; i < x_len; i++)
        x_value = x_pad + x_value;
    return x_value;
}
var g_seiZen = '０１２３４５６７８９ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ＿　�\�|�`';
var g_seiHan = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxzy_ --~';

function SeiZenToHan(x_value) {
    if (x_value == null)
        return x_value;
    var p_ret = null;
    for (var i = 0; i < x_value.length; i++) {
        var p_ch = x_value.charAt(i);
        var p_idx = g_seiZen.indexOf(p_ch);
        if (p_idx >= 0) {
            if (p_ret == null)
                p_ret = x_value.substring(0, i);
            p_ret += g_seiHan.charAt(p_idx);
        } else {
            if (p_ret != null)
                p_ret += p_ch;
        }
    }
    return p_ret || x_value;
}
var g_seiWarekiEra = {
    'MEIJI': new Array(1868, '明治', '明治', 'MEIJI', '明', 'M', 'm', '㍾'),
    'TAISHO': new Array(1912, '大正', '大正', 'TAISHO', 'TAISYO', 'TAISHOU',
        'TAISYOU', '大', 'T', 't', '㍽'),
    'SHOWA': new Array(1926, '昭和', '昭和', 'SHOWA', 'SHOUWA', 'SYOWA', 'SYOUWA',
        '昭', 'S', 's', '㍼'),
    'HEISEI': new Array(1989, '平成', '平成', 'HEISEI', '平', 'H', 'h', '㍻')
};

function SeiParseDateDayOfWeek(x_value) {
    var p_dow = ['日', '日曜日', '月', '月曜日', '火', '火曜日', '水', '水曜日', '木', '木曜日',
        '金', '金曜日', '土', '土曜日'
    ];
    for (var i = 0; i < p_dow.length; i++) {
        if (p_dow[i] == x_value)
            return i / 2;
    }
    return -1;
}

function SeiParseDate(x_value) {
    var p_today = SeiToday();
    var p_val = SeiTrim(SeiZenToHan(x_value));
    if (p_val == '')
        return null;
    var p_dom = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    var p_y = NaN,
        p_m = NaN,
        p_d = NaN;
    if ((p_val.length == 1 || p_val.length == 2) && !isNaN(SeiParseInt(p_val))) {
        p_y = p_today.getFullYear();
        p_m = p_today.getMonth() + 1;
        p_d = parseInt(p_val, 10);
    }
    if ((p_val.length == 3 || p_val.length == 4) && !isNaN(SeiParseInt(p_val))) {
        p_y = p_today.getFullYear();
        p_m = p_val.substring(0, p_val.length - 2);
        p_d = p_val.substring(p_val.length - 2);
        if (p_d.charAt(0) == '0')
            p_d = p_d.substring(1);
        if (p_m.charAt(0) == '0')
            p_m = p_m.substring(1);
        p_d = SeiParseInt(p_d);
        p_m = SeiParseInt(p_m);
    }
    if (p_val.length == 6 && !isNaN(SeiParseInt(p_val))) {
        p_y = Number(p_val.substring(0, 2));
        p_m = Number(p_val.substring(2, 4));
        p_d = Number(p_val.substring(4, 6));
    } else if (p_val.length == 7 && !isNaN(SeiParseInt(p_val))) {
        p_y = Number(p_val.substring(0, 3));
        p_m = Number(p_val.substring(3, 5));
        p_d = Number(p_val.substring(5, 7));
    } else if (p_val.length == 8 && !isNaN(SeiParseInt(p_val))) {
        p_y = Number(p_val.substring(0, 4));
        p_m = Number(p_val.substring(4, 6));
        p_d = Number(p_val.substring(6, 8));
    }
    if (!isNaN(p_y) && !isNaN(p_m) && !isNaN(p_d)) {
        if (p_y < 0)
            return null;
        if (p_y < 100) {
            if (p_y < 80)
                p_y += 2000;
            else
                p_y += 1900;
        }
        if (p_m <= 0 || 12 < p_m)
            return null;
        if ((p_y % 4) == 0 && ((p_y % 100) != 0 || (p_y % 400) == 0)) {
            p_dom[1]++;
        }
        if (p_d <= 0 || p_d > p_dom[p_m - 1])
            return null;
        return new Date(p_y, p_m - 1, p_d).getTime() + 1000;
    }
    var p_date = p_val,
        p_time = '';
    if (p_val.lastIndexOf(' ') > 0) {
        var p_idx = p_val.lastIndexOf(' ');
        p_date = p_val.substring(0, p_idx);
        p_time = p_val.substring(p_idx + 1);
    } else if (p_val.length >= 10 && p_val.lastIndexOf('-') == p_val.length - 9) {
        var p_idx = p_val.lastIndexOf('-');
        p_date = p_val.substring(0, p_idx);
        p_time = p_val.substring(p_idx + 1);
    }
    if (p_date == '')
        return null;
    var p_sldix = p_date.indexOf('/');
    var p_midix = p_date.indexOf('-');
    var p_dodix = p_date.indexOf('.');
    var p_delim = '/',
        p_count = 0;
    if (p_sldix < 0 && p_dodix < 0 || p_midix > 0 && p_midix < p_sldix)
        p_delim = '-';
    else if (p_sldix < 0 && p_dodix > 0)
        p_delim = '.';
    p_y = p_m = p_d = NaN;
    var p_pos = 0,
        p_pos2 = 0;
    while ((p_pos2 = p_date.indexOf(p_delim, p_pos)) >= 0) {
        if (p_count == 0)
            p_y = Number(p_val.substring(p_pos, p_pos2));
        if (p_count == 1)
            p_m = Number(p_val.substring(p_pos, p_pos2));
        p_pos = p_pos2 + p_delim.length;
        if (++p_count >= 3)
            return null;
    }
    if (p_count == 1) {
        p_d = Number(p_val.substring(p_pos));
        p_m = p_y;
        p_y = 2017;
    } else if (p_count == 2) {
        p_d = Number(p_val.substring(p_pos));
    } else if (p_count == 0) {
        return null;
    }
    if (!isNaN(p_y) && !isNaN(p_m) && !isNaN(p_d)) {
        if (p_y < 100) {
            if (p_y < 80)
                p_y += 2000;
            else
                p_y += 1900;
        }
        if (p_m <= 0 || 12 < p_m)
            return null;
        if ((p_y % 4) == 0 && ((p_y % 100) != 0 || (p_y % 400) == 0))
            p_dom[1]++;
        if (p_d <= 0 || p_d > p_dom[p_m - 1])
            return null;
        return new Date(p_y, p_m - 1, p_d).getTime() + 1000;
    }
    return null;
}

function SeiParseDateWarekiDayOfWeek(x_value) {
    var p_dow = ['日', '日曜日', '月', '月曜日', '火', '火曜日', '水', '水曜日', '木', '木曜日',
        '金', '金曜日', '土', '土曜日'
    ];
    for (var i = 0; i < p_dow.length; i++) {
        if (p_dow[i] == x_value)
            return i / 2;
    }
    return -1;
}

function SeiParseDateWareki(x_value) {
    var p_today = SeiToday();
    var p_val = SeiTrim(SeiZenToHan(x_value));
    if (p_val == '')
        return null;
    var p_dom = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    for (var i in g_seiWarekiEra) {
        var p_era = g_seiWarekiEra[i];
        for (var j = 1; j < p_era.length; j++) {
            if (p_val.indexOf(p_era[j]) != 0 ||
                isNaN(SeiParseInt(p_val.substring(p_era[j].length))))
                continue;
            var p_val2 = p_val;
            p_val = p_val.substring(p_era[j].length)
            if (p_val.length == 6) {
                var p_y, p_m, p_d;
                p_y = Number(p_val.substring(0, 2));
                p_m = Number(p_val.substring(2, 4));
                p_d = Number(p_val.substring(4, 6));
                if (p_y > 0 && 0 < p_m && p_m <= 12 && p_d > 0) {
                    p_y += p_era[0] - 1;
                    if ((p_y % 4) == 0 &&
                        ((p_y % 100) != 0 || (p_y % 400) == 0))
                        p_dom[1]++;
                    if (p_d <= p_dom[p_m - 1])
                        return new Date(p_y, p_m - 1, p_d).getTime() + 1000;
                }
            }
            p_val = p_val2;
        }
    }
    var p_y = NaN,
        p_m = NaN,
        p_d = NaN;
    if ((p_val.length == 1 || p_val.length == 2) && !isNaN(SeiParseInt(p_val))) {
        p_y = p_today.getFullYear();
        p_m = p_today.getMonth() + 1;
        p_d = parseInt(p_val, 10);
    }
    if ((p_val.length == 3 || p_val.length == 4) && !isNaN(SeiParseInt(p_val))) {
        p_y = p_today.getFullYear();
        p_m = p_val.substring(0, p_val.length - 2);
        p_d = p_val.substring(p_val.length - 2);
        if (p_d.charAt(0) == '0')
            p_d = p_d.substring(1);
        if (p_m.charAt(0) == '0')
            p_m = p_m.substring(1);
        p_d = SeiParseInt(p_d);
        p_m = SeiParseInt(p_m);
    }
    if (p_val.length == 6 && !isNaN(SeiParseInt(p_val))) {
        p_y = Number(p_val.substring(0, 2));
        p_m = Number(p_val.substring(2, 4));
        p_d = Number(p_val.substring(4, 6));
    } else if (p_val.length == 7 && !isNaN(SeiParseInt(p_val))) {
        p_y = Number(p_val.substring(0, 3));
        p_m = Number(p_val.substring(3, 5));
        p_d = Number(p_val.substring(5, 7));
    } else if (p_val.length == 8 && !isNaN(SeiParseInt(p_val))) {
        p_y = Number(p_val.substring(0, 4));
        p_m = Number(p_val.substring(4, 6));
        p_d = Number(p_val.substring(6, 8));
    }
    if (!isNaN(p_y) && !isNaN(p_m) && !isNaN(p_d)) {
        if (p_y < 0)
            return null;
        if (p_y < 100) {
            p_y += 1989 - 1;
        }
        if (p_m <= 0 || 12 < p_m)
            return null;
        if ((p_y % 4) == 0 && ((p_y % 100) != 0 || (p_y % 400) == 0)) {
            p_dom[1]++;
        }
        if (p_d <= 0 || p_d > p_dom[p_m - 1])
            return null;
        return new Date(p_y, p_m - 1, p_d).getTime() + 1000;
    }
    var p_date = p_val,
        p_time = '';
    if (p_val.lastIndexOf(' ') > 0) {
        var p_idx = p_val.lastIndexOf(' ');
        p_date = p_val.substring(0, p_idx);
        p_time = p_val.substring(p_idx + 1);
    } else if (p_val.length >= 10 && p_val.lastIndexOf('-') == p_val.length - 9) {
        var p_idx = p_val.lastIndexOf('-');
        p_date = p_val.substring(0, p_idx);
        p_time = p_val.substring(p_idx + 1);
    }
    if (p_date == '')
        return null;
    var p_sldix = p_date.indexOf('/');
    var p_midix = p_date.indexOf('-');
    var p_dodix = p_date.indexOf('.');
    var p_delim = '/',
        p_count = 0;
    if (p_sldix < 0 && p_dodix < 0 || p_midix > 0 && p_midix < p_sldix)
        p_delim = '-';
    else if (p_sldix < 0 && p_dodix > 0)
        p_delim = '.';
    p_y = p_m = p_d = NaN;
    var p_yidx = p_date.indexOf('年');
    if (p_yidx > 0) {
        var p_midx = p_date.indexOf('月', p_yidx + 1);
        if (p_midx > 0) {
            var p_didx = p_date.indexOf('日', p_midx + 1);
            if (p_didx > 0) {
                p_y = SeiTrim(p_date.substring(0, p_yidx));
                p_m = SeiTrim(p_date.substring(p_yidx + 1, p_midx))
                p_d = SeiTrim(p_date.substring(p_midx + 1, p_didx));
                p_count = 3;
            }
        }
    }
    if (p_y == null || isNaN(p_y)) {
        var p_pos = 0,
            p_pos2 = 0;
        while ((p_pos2 = p_date.indexOf(p_delim, p_pos)) >= 0) {
            if (p_count == 0)
                p_y = p_val.substring(p_pos, p_pos2);
            if (p_count == 1)
                p_m = Number(p_val.substring(p_pos, p_pos2));
            p_pos = p_pos2 + p_delim.length;
            if (++p_count >= 3)
                return null;
        }
        if (p_count == 1) {
            p_d = Number(p_val.substring(p_pos));
            p_m = p_y;
            p_y = 2017;
        } else if (p_count == 2) {
            p_d = Number(p_val.substring(p_pos));
        } else if (p_count == 0) {
            return null;
        }
    }
    if (isNaN(Number(p_y)) && p_y.length) {
        for (var i in g_seiWarekiEra) {
            var p_era = g_seiWarekiEra[i];
            for (var j = 1; j < p_era.length; j++) {
                if (p_y.length < p_era[j].length ||
                    p_y.substring(0, p_era[j].length) != p_era[j])
                    continue;
                var p_yn = Number(p_y.substring(p_era[j].length));
                if (isNaN(p_yn))
                    continue;
                p_y = p_yn + p_era[0] - 1;
                break;
            }
            if (!isNaN(p_y))
                break;
        }
    } else {
        p_y = Number(p_y);
    }
    if (!isNaN(p_y) && !isNaN(p_m) && !isNaN(p_d)) {
        if (p_y < 100) {
            p_y += 1989 - 1;
        }
        if (p_m <= 0 || 12 < p_m)
            return null;
        if ((p_y % 4) == 0 && ((p_y % 100) != 0 || (p_y % 400) == 0))
            p_dom[1]++;
        if (p_d <= 0 || p_d > p_dom[p_m - 1])
            return null;
        return new Date(p_y, p_m - 1, p_d).getTime() + 1000;
    }
    return null;
}

function SeiParseDateYM(x_value) {
    var p_today = SeiToday();
    var p_val = SeiTrim(SeiZenToHan(x_value));
    if (p_val == '')
        return null;
    var p_y = NaN,
        p_m = NaN;
    if ((p_val.length == 1 || p_val.length == 2) && !isNaN(SeiParseInt(p_val))) {
        p_y = p_today.getFullYear();
        p_m = parseInt(p_val, 10);
    }
    if (p_val.length == 4 && !isNaN(SeiParseInt(p_val))) {
        p_y = Number(p_val.substring(0, 2));
        p_m = Number(p_val.substring(2, 4));
        if (p_y < 80)
            p_y += 2000;
        else
            p_y += 1900;
    }
    if (p_val.length == 5 && !isNaN(SeiParseInt(p_val))) {
        p_y = Number(p_val.substring(0, 2));
        p_m = Number(p_val.substring(2, 5));
    }
    if (p_val.length == 6 && !isNaN(SeiParseInt(p_val))) {
        p_y = Number(p_val.substring(0, 4));
        p_m = Number(p_val.substring(4, 6));
    }
    if (!isNaN(p_y) && !isNaN(p_m)) {
        if (p_y < 0)
            return null;
        if (p_m <= 0 || 12 < p_m)
            return null;
        return new Date(p_y, p_m - 1, 1).getTime() + 1000;
    }
    var p_date = p_val;
    if (p_val.lastIndexOf(' ') > 0)
        return null;
    if (p_date == '')
        return null;
    var p_sldix = p_date.indexOf('/');
    var p_midix = p_date.indexOf('-');
    var p_dodix = p_date.indexOf('.');
    var p_delim = '/',
        p_count = 0;
    if (p_sldix < 0 && p_dodix < 0 || p_midix > 0 && p_midix < p_sldix)
        p_delim = '-';
    else if (p_sldix < 0 && p_dodix > 0)
        p_delim = '.';
    p_y = p_m = p_d = NaN;
    var p_pos = p_date.indexOf(p_delim);
    if (p_pos >= 0 && p_date.indexOf(p_delim, p_pos + 1) < 0) {
        p_y = p_date.substring(0, p_pos);
        p_m = p_date.substring(p_pos + 1);
    }
    p_y = Number(p_y);
    if (!isNaN(p_y) && !isNaN(p_m)) {
        if (p_y < 100) {
            if (p_y < 80)
                p_y += 2000;
            else
                p_y += 1900;
        }
        if (p_m <= 0 || 12 < p_m)
            return null;
        return new Date(p_y, p_m - 1, 1).getTime() + 1000;
    }
    return null;
}

function SeiParseDateYMWareki(x_value) {
    var p_today = SeiToday();
    var p_val = SeiTrim(SeiZenToHan(x_value));
    if (p_val == '')
        return null;
    for (var i in g_seiWarekiEra) {
        var p_era = g_seiWarekiEra[i];
        for (var j = 1; j < p_era.length; j++) {
            if (p_val.indexOf(p_era[j]) != 0 ||
                isNaN(SeiParseInt(p_val.substring(p_era[j].length))))
                continue;
            var p_val2 = p_val;
            p_val = p_val.substring(p_era[j].length)
            if (p_val.length == 4) {
                var p_y, p_m, p_d;
                p_y = Number(p_val.substring(0, 2));
                p_m = Number(p_val.substring(2, 4));
                if (p_y > 0 && 0 < p_m && p_m <= 12) {
                    p_y += p_era[0] - 1;
                    return new Date(p_y, p_m - 1, 1).getTime() + 1000;
                }
            }
            p_val = p_val2;
        }
    }
    var p_y = NaN,
        p_m = NaN;
    if ((p_val.length == 1 || p_val.length == 2) && !isNaN(SeiParseInt(p_val))) {
        p_y = p_today.getFullYear();
        p_m = parseInt(p_val, 10);
    }
    if (p_val.length == 4 && !isNaN(SeiParseInt(p_val))) {
        p_y = Number(p_val.substring(0, 2));
        p_m = Number(p_val.substring(2, 4));
        p_y += 1989 - 1;
    }
    if (p_val.length == 5 && !isNaN(SeiParseInt(p_val))) {
        p_y = Number(p_val.substring(0, 2));
        p_m = Number(p_val.substring(2, 5));
    }
    if (p_val.length == 6 && !isNaN(SeiParseInt(p_val))) {
        p_y = Number(p_val.substring(0, 4));
        p_m = Number(p_val.substring(4, 6));
    }
    if (!isNaN(p_y) && !isNaN(p_m)) {
        if (p_y < 0)
            return null;
        if (p_m <= 0 || 12 < p_m)
            return null;
        return new Date(p_y, p_m - 1, 1).getTime() + 1000;
    }
    var p_date = p_val;
    if (p_val.lastIndexOf(' ') > 0)
        return null;
    if (p_date == '')
        return null;
    var p_sldix = p_date.indexOf('/');
    var p_midix = p_date.indexOf('-');
    var p_dodix = p_date.indexOf('.');
    var p_delim = '/',
        p_count = 0;
    if (p_sldix < 0 && p_dodix < 0 || p_midix > 0 && p_midix < p_sldix)
        p_delim = '-';
    else if (p_sldix < 0 && p_dodix > 0)
        p_delim = '.';
    p_y = p_m = p_d = NaN;
    var p_yidx = p_date.indexOf('年');
    if (p_yidx > 0) {
        var p_midx = p_date.indexOf('月', p_yidx + 1);
        if (p_midx > 0) {
            p_y = SeiTrim(p_date.substring(0, p_yidx));
            p_m = SeiTrim(p_date.substring(p_yidx + 1, p_midx))
            p_count = 2;
        }
    }
    if (p_y == null || isNaN(p_y)) {
        var p_pos = p_date.indexOf(p_delim);
        if (p_pos >= 0 && p_date.indexOf(p_delim, p_pos + 1) < 0) {
            p_y = p_date.substring(0, p_pos);
            p_m = p_date.substring(p_pos + 1);
        }
    }
    if (isNaN(Number(p_y)) && p_y.length) {
        for (var i in g_seiWarekiEra) {
            var p_era = g_seiWarekiEra[i];
            for (var j = 1; j < p_era.length; j++) {
                if (p_y.length < p_era[j].length ||
                    p_y.substring(0, p_era[j].length) != p_era[j])
                    continue;
                var p_yn = Number(p_y.substring(p_era[j].length));
                if (isNaN(p_yn))
                    continue;
                p_y = p_yn + p_era[0] - 1;
                break;
            }
            if (!isNaN(p_y))
                break;
        }
    } else {
        p_y = Number(p_y);
    }
    p_y = Number(p_y);
    if (!isNaN(p_y) && !isNaN(p_m)) {
        if (p_y < 100) {
            p_y += 1989 - 1;
        }
        if (p_m <= 0 || 12 < p_m)
            return null;
        return new Date(p_y, p_m - 1, 1).getTime() + 1000;
    }
    return null;
}

function SeiEditDate(x_date) {
    if (x_date == null || x_date == 0) {
        return x_date;
    }
    var p_date = new Date(x_date);
    return p_date.getFullYear() + '/' + (p_date.getMonth() < 9 ? '0' : '') +
        (p_date.getMonth() + 1) + '/' +
        (p_date.getDate() < 10 ? '0' : '') + p_date.getDate();
}

function SeiEditDateWareki(x_date) {
    if (x_date == null || x_date == 0) {
        return x_date;
    }
    var p_date = new Date(x_date);
    var p_year = p_date.getFullYear();
    if (-3197178000000 <= p_date.getTime() && p_date.getTime() < -1812186000001)
        p_year = '明治' + (p_year - 1868 + 1);
    if (-1812186000000 <= p_date.getTime() && p_date.getTime() < -1357635600001)
        p_year = '大正' + (p_year - 1912 + 1);
    if (-1357635600000 <= p_date.getTime() && p_date.getTime() < 600188399999)
        p_year = '昭和' + (p_year - 1926 + 1);
    if (p_date.getTime() >= 600188400000)
        p_year = '平成' + (p_year - 1989 + 1);
    return p_year + '年' + (p_date.getMonth() < 9 ? '0' : '') +
        (p_date.getMonth() + 1) + '月' +
        (p_date.getDate() < 10 ? '0' : '') + p_date.getDate() + '日';
}

function SeiEditDateYM(x_date) {
    if (x_date == null || x_date == 0) {
        return x_date;
    }
    var p_date = new Date(x_date);
    return p_date.getFullYear() + '/' + (p_date.getMonth() < 9 ? '0' : '') +
        (p_date.getMonth() + 1);
}

function SeiEditDateYMWareki(x_date) {
    if (x_date == null || x_date == 0) {
        return x_date;
    }
    var p_date = new Date(x_date);
    var p_year = p_date.getFullYear();
    if (-3197178000000 <= p_date.getTime() && p_date.getTime() < -1812186000001)
        p_year = '明治' + (p_year - 1868 + 1);
    if (-1812186000000 <= p_date.getTime() && p_date.getTime() < -1357635600001)
        p_year = '大正' + (p_year - 1912 + 1);
    if (-1357635600000 <= p_date.getTime() && p_date.getTime() < 600188399999)
        p_year = '昭和' + (p_year - 1926 + 1);
    if (p_date.getTime() >= 600188400000)
        p_year = '平成' + (p_year - 1989 + 1);
    return p_year + '年' + (p_date.getMonth() < 9 ? '0' : '') +
        (p_date.getMonth() + 1) + '月';
}

function SeiParseTime(x_value) {
    var p_val = SeiTrim(SeiZenToHan(x_value));
    if (p_val == '')
        return null;
    var p_h = NaN,
        p_m = NaN,
        p_s = NaN;
    if (!isNaN(SeiParseInt(p_val))) {
        if (p_val.length > 6)
            return null;
        if ((p_val.length == 1 || p_val.length == 2)) {
            p_h = Number(p_val);
            p_m = 0;
            p_s = 0;
        } else if (p_val.length == 3) {
            p_h = Number(p_val.substring(0, 1));
            p_m = Number(p_val.substring(1, 3));
            p_s = 0;
        } else if (p_val.length == 4) {
            p_h = Number(p_val.substring(0, 2));
            p_m = Number(p_val.substring(2, 4));
            p_s = 0;
        } else if (p_val.length == 5) {
            p_h = Number(p_val.substring(0, 2));
            p_m = Number(p_val.substring(2, 4));
            p_s = Number(p_val.substring(4, 5));
        } else if (p_val.length == 6) {
            p_h = Number(p_val.substring(0, 2));
            p_m = Number(p_val.substring(2, 4));
            p_s = Number(p_val.substring(4, 6));
        }
    } else {
        var p_time = p_val;
        var p_count = 0;
        var p_pos = 0,
            p_pos2 = 0;
        while ((p_pos2 = p_time.indexOf(':', p_pos)) >= 0 ||
            (p_pos2 = p_time.indexOf('：', p_pos)) >= 0) {
            if (p_count == 0)
                p_h = SeiParseInt(p_val.substring(p_pos, p_pos2));
            if (p_count == 1)
                p_m = SeiParseInt(p_val.substring(p_pos, p_pos2));
            if (p_count == 2)
                p_s = SeiParseInt(p_val.substring(p_pos, p_pos2));
            p_pos = p_pos2 + 1;
            if (++p_count >= 3)
                return null;
        }
        if (p_count == p_time.length) {
            return null;
        }
        if (p_count == 1) {
            p_m = SeiParseInt(p_val.substring(p_pos));
            p_s = 0;
        } else if (p_count == 2) {
            p_s = SeiParseInt(p_val.substring(p_pos));
        } else if (p_count == 0) {
            return null;
        }
    }
    if (!isNaN(p_h) && !isNaN(p_m) && !isNaN(p_s)) {
        if (p_h < 0 || 24 < p_h)
            return null;
        if (p_m < 0 || 59 < p_m)
            return null;
        if (p_s < 0 || 59 < p_s)
            return null;
        var p_time = new Date(0);
        p_time.setHours(p_h);
        p_time.setMinutes(p_m);
        p_time.setSeconds(p_s);
        return p_time;
    }
    return null;
}

function SeiParseTimeHM(x_value) {
    var p_val = SeiTrim(SeiZenToHan(x_value));
    if (p_val == '')
        return null;
    var p_h = NaN,
        p_m = NaN;
    if (!isNaN(SeiParseInt(p_val))) {
        if (p_val.length > 4)
            return null;
        if ((p_val.length == 1 || p_val.length == 2)) {
            p_h = Number(p_val);
            p_m = 0;
        } else if (p_val.length == 3) {
            p_h = Number(p_val.substring(0, 1));
            p_m = Number(p_val.substring(1, 3));
        } else if (p_val.length == 4) {
            p_h = Number(p_val.substring(0, 2));
            p_m = Number(p_val.substring(2, 4));
        }
    } else {
        var p_time = p_val;
        var p_count = 0;
        var p_pos = 0,
            p_pos2 = 0;
        while ((p_pos2 = p_time.indexOf(':', p_pos)) >= 0 ||
            (p_pos2 = p_time.indexOf('：', p_pos)) >= 0) {
            if (p_count == 0)
                p_h = SeiParseInt(p_val.substring(p_pos, p_pos2));
            if (p_count == 1)
                p_m = SeiParseInt(p_val.substring(p_pos, p_pos2));
            p_pos = p_pos2 + 1;
            if (++p_count >= 2)
                return null;
        }
        if (p_count == p_time.length) {
            return null;
        }
        if (p_count == 1) {
            p_m = SeiParseInt(p_val.substring(p_pos));
        } else if (p_count == 0) {
            return null;
        }
    }
    if (!isNaN(p_h) && !isNaN(p_m)) {
        if (p_h < 0 || 24 < p_h)
            return null;
        if (p_m < 0 || 59 < p_m)
            return null;
        var p_time = new Date(0);
        p_time.setHours(p_h);
        p_time.setMinutes(p_m);
        return p_time;
    }
}

function SeiEditTime(x_time) {
    if (x_time == null || x_time == 0) {
        return x_time;
    }
    var p_time = new Date(x_time);
    return (p_time.getHours() < 10 ? '0' : '') + (p_time.getHours()) + ':' +
        (p_time.getMinutes() < 10 ? '0' : '') + (p_time.getMinutes()) +
        ':' + (p_time.getSeconds() < 10 ? '0' : '') +
        (p_time.getSeconds());
}

function SeiEditTimeHM(x_time) {
    if (x_time == null || x_time == 0) {
        return x_time;
    }
    var p_time = new Date(x_time);
    return (p_time.getHours() < 10 ? '0' : '') + (p_time.getHours()) + ':' +
        (p_time.getMinutes() < 10 ? '0' : '') + (p_time.getMinutes());
}

function SeiEditDate2(x_value, x_type) {
    if (x_value == null) {
        return x_value;
    }
    var p_value = SeiTrim(x_value),
        p_sub = ''
    if (x_type == 'd' || x_type == 'z' || x_type == 'S') {
        var p_idx = p_value.indexOf(' ');
        if (p_idx > 0) {
            p_sub = p_value.substring(p_idx);
            p_value = p_value.substring(0, p_idx);
        }
    }
    var p_date = SeiParseDate(p_value);
    if (p_date == null) {
        return x_value;
    }
    return SeiEditDate(p_date) + p_sub;
}

function SeiSaveRadioStatus(x_radio) {
    var p_radio = x_radio.form.elements[x_radio.name];
    if (p_radio.length) {
        x_radio.g_pms_radio_status = new Array();
        for (var i = 0; i < p_radio.length; i++) {
            x_radio.g_pms_radio_status[i] = p_radio[i].checked;
        }
    } else {
        x_radio.g_pms_radio_status = p_radio.checked;
    }
}

function SeiRestoreRadioStatus(x_radio) {
    if (typeof x_radio.g_pms_radio_status == 'undefined')
        return




    var p_radio = x_radio.form.elements[x_radio.name];
    if (p_radio.length) {
        for (var i = 0; i < p_radio.length; i++) {
            p_radio[i].checked = x_radio.g_pms_radio_status[i];
        }
    } else {
        p_radio.checked = x_radio.g_pms_radio_status;
    }
}

function SeiSaveSelectStatus(x_sel) {
    x_sel.g_pms_selected_index = x_sel.selectedIndex;
}

function SeiRestoreSelectStatus(x_sel) {
    if (typeof x_sel.g_pms_selected_index == 'undefined')
        return;
    x_sel.selectedIndex = x_sel.g_pms_selected_index;
}

function SeiCheckFileName(x_form) {
    if (!x_form)
        return null;
    var p_elem = x_form.elements,
        p_len = p_elem.length,
        p_ret = [];
    for (var i = 0; i < p_len; i++) {
        if (p_elem[i].type != 'file' || p_elem[i].value.length <= 0)
            continue;
        var p_file = p_elem[i].value;
        if (p_file.length >= 2 &&
            p_file.charAt(1) == ':' &&
            ('a' <= p_file.charAt(0) && p_file.charAt(0) <= 'z' || 'A' <= p_file
                .charAt(0) &&
                p_file.charAt(0) <= 'Z'))
            continue;
        if (p_file.charAt(0) == '\\')
            continue;
        p_ret.push(p_elem[i]);
    }
    if (p_ret.length > 0)
        return p_ret;
    return null;
}

function SeiCheckFileName2(x_form) {
    var p_ifile = SeiCheckFileName(x_form);
    if (p_ifile == null || p_ifile.length == 0)
        return true;
    alert('不明なファイル名: ' + p_ifile[0].value);
    return false;
}

function SeiCheckFileName3(x_form) {
    var p_ifile = SeiCheckFileName(x_form);
    if (p_ifile == null)
        return true;
    for (var i = 0; i < p_ifile.length; i++) {
        p_ifile[i].disabled = true;
    }
    return true;
}

function SeiDummy() {
    if (parent && parent.fr_dmy) {
        return parent.fr_dmy;
    }
    var p_win = window;
    while (p_win) {
        if (p_win.top) {
            p_win = p_win.top;
        }
        if (p_win.fr_dmy) {
            return p_win.fr_dmy;
        }
        p_win = p_win.opener;
    }
    return null;
}

function SeiJump2(x_target, x_url) {
    var p_fragm = '',
        p_idx = x_url.indexOf('#');
    if (p_idx > 0) {
        p_fragm = x_url.substring(p_idx, x_url.length);
        x_url = x_url.substring(0, p_idx);
    }
    if (x_url.indexOf('?') < 0) {
        x_url += '?';
    } else {
        x_url += '&';
    }
    x_url += 'sei_page_x=' + SeiGetScrollPos().left + '&sei_page_y=' +
        SeiGetScrollPos().top;
    if (typeof(g_seiMenuFrameCols) == 'undefined') {
        g_seiMenuFrameCols = null;
    }
    if (g_seiMenuFrameCols) {
        x_url += '&sei_menuframe_cols=' + g_seiMenuFrameCols;
    }
    if (typeof(g_seiMenuFrameColsOrg) == 'undefined') {
        g_seiMenuFrameColsOrg = null;
    }
    if (g_seiMenuFrameColsOrg) {
        x_url += '&sei_menuframe_colsorg=' + g_seiMenuFrameColsOrg;
    }
    x_target.location = x_url + p_fragm;
}

function SeiInvalidateScreen(x_cursor, x_msg) {
    if (typeof x_cursor == 'undefined')
        x_cursor = 'wait';
    if (typeof x_msg == 'undefined')
        x_msg = 'しばらくお待ち下さい。';
    document.body.style.cursor = x_cursor;
    document.body.title = x_msg;

    function _invalidate(x_input, x_len, x_pos) {
        if (x_pos >= x_len)
            return;
        var p_end = Math.min(x_len, x_pos + 10);
        if (p_end <= x_pos)
            p_end = x_pos + 1;
        for (var i = x_pos; i < p_end; i++) {
            x_input[i].disabled = true;
        }
        if (p_end < x_len)
            setTimeout(function() {
                _invalidate(x_input, x_len, p_end);
            }, 0);
    }
    setTimeout(
        function() {
            var p_links = document.links,
                p_lnlen = p_links.length;
            for (var i = 0; i < p_lnlen; i++) {
                var p_link = p_links[i];
                p_link.disabled = true;
                p_link.href = '#';
                p_link.onclick = 'return false';
                var p_parent = p_link.parentNode;
                if (typeof p_parent == 'object') {
                    p_parent.onclick = 'return false';
                    if (typeof p_parent.style != 'undefined')
                        p_parent.style.cursor = x_cursor;
                }
            }
            var p_images = document.images,
                p_imglen = p_images.length;
            for (var i = 0; i < p_imglen; i++) {
                var p_img = p_images[i];
                if (typeof p_img.parentNode == 'object') {
                    p_img.parentNode.onclick = 'return false';
                }
            }
            for (var i = 0; i < document.forms.length; i++) {
                var p_elems = document.forms[i].elements,
                    p_elen = p_elems.length,
                    p_elems2 = [];
                for (var i = 0; i < p_elen; i++) {
                    if (p_elems[i].type != 'hidden')
                        p_elems2.push(p_elems[i]);
                }
                _invalidate(p_elems2, p_elems2.length, 0);
            }
        }, 10);
}

function SeiAddEventListener(x_elem, x_type, x_func, x_param) {
    if (!x_elem)
        return false;
    var p_func = x_func;
    if (typeof(x_param) != 'undefined')
        p_func = function() {
            var p_arg = new Array();
            for (var i = 0; i < arguments.length; i++)
                p_arg.push(arguments[i]);
            p_arg.push(x_param);
            x_func.apply(this, p_arg);
        };
    x_elem.attachEvent('on' + x_type, p_func);
}
var g_seiWinCallbacks;

function SeiAddWinCallback(x_field, x_func, x_param) {
    if (!x_field)
        return false;
    var p_func = x_func;
    if (typeof(x_param) != 'undefined') {
        p_func = function(x_arrayno) {
            x_func.apply(this, arguments);
        };
    }
    if (!g_seiWinCallbacks)
        g_seiWinCallbacks = new Array();
    g_seiWinCallbacks[x_field] = p_func;
}

function SeiExecWinCallback(x_fr_main, x_fields, x_arrayno) {
    if (!g_seiWinCallbacks)
        return;
    for (var i = 0; i < x_fields.length; i++) {
        var p_func = g_seiWinCallbacks[x_fields[i]];
        if (p_func)
            p_func(x_arrayno);
    }
    var p_func = g_seiWinCallbacks['*'];
    if (p_func)
        p_func(x_arrayno);
}

function SeiRemoveFromArray(x_array, x_value) {
    if (typeof(x_array) == 'undefined' || !x_array)
        return x_array;
    for (var i = 0; i < x_array.length; i++) {
        if (x_array[i] != x_value)
            continue;
        var p_array = [];
        for (var j = 0; j < i; j++)
            p_array.push(x_array[j]);
        for (var j = i + 1; j < x_array.length; j++)
            p_array.push(x_array[j]);
        x_array = p_array;
    }
    return x_array;
}
var g_seiEditHighlightOnClick = true;

function SeiEditHighlight(x_form, x_field) {
    if (!x_field)
        return;
    var p_field = null;
    if (x_field.length && !x_field.options) {
        p_field = x_field[0];
    } else {
        p_field = x_field;
    }
    var p_highlight = x_form.pms_edit_highlight;
    if (p_highlight) {
        if (p_highlight.value == '') {
            p_highlight.value = p_field.name;
        } else if (p_highlight.value.indexOf(p_field.name) < 0) {
            p_highlight.value += ',' + p_field.name;
        } else {
            var p_fields = p_highlight.value.split(',');
            for (var i in p_fields) {
                if (this[i] == p_field.name) {
                    return;
                }
            }
            p_fields.push(p_field.name);
            p_highlight.value = p_fields.join(',');
        }
    }
    if (g_seiEditHighlightOnClick) {
        var p_parent = p_field.parentNode;
        var p_count = 0;
        while (p_parent && p_count < 10) {
            if (p_parent.tagName == 'TD') {
                p_parent.className = 'SeiEditHighlightTD';
                break;
            }
            p_parent = p_parent.parentNode;
            p_count++;
        }
    }
}

function SeiCancelKeyEvent(x_ev) {
    event.returnValue = false;
    event.cancelBubble = true;
    event.keyCode = 0;
}
var g_seiCashFields;
var g_seiFieldsArray;

function SeiGetFieldsArray(x_formnm, x_prenm, x_array) {
    var p_form = document.forms[x_formnm];
    if (!p_form) {
        return null;
    }
    if (!g_seiFieldsArray) {
        g_seiFieldsArray = {};
    }
    var p_arraycnt = -1;
    if (x_array) {
        p_arraycnt = parseInt(p_form.elements[(x_prenm ? x_prenm : '') +
            'arraycount'].value);
    }
    var p_arguments = new Array();
    for (var i = 0; i < p_arguments.length; i++) {
        p_arguments.push();
    }
    if (arguments.length > 3 && typeof arguments[3] == 'object') {
        var p_obj = arguments[3];
        for (var i = 0; i < p_obj.length; i++) {
            p_arguments[3 + i] = p_obj[i];
        }
    }
    for (var i = 3; i < p_arguments.length; i++) {
        var p_name = p_arguments[i];
        if (g_seiFieldsArray[p_name]) {
            continue;
        }
        if (x_array) {
            var p_array = new Array();
            for (var j = 0; j < p_arraycnt; j++) {
                p_array.push(p_form.elements[p_name + '_' + j]);
            }
            g_seiFieldsArray[p_name] = p_array;
        } else {
            g_seiFieldsArray[p_name] = p_form.elements[p_name];
        }
    }
    return g_seiFieldsArray;
}

function SeiSetTimeout(x_func, x_arguments, x_timeout) {
    return window.setTimeout(function() {
        x_func.apply(this, x_arguments);
    }, x_timeout);
}

function SeiIsEmpty(x_obj) {
    if (x_obj == null) {
        return true;
    }
    switch (typeof(x_obj)) {
        case 'undefined':
            return true;
        case 'string':
            return x_obj.length == 0;
        case 'number':
            return isNaN(x_obj);
        case 'boolean':
            return false;
        case 'object':
            if (!isNaN(x_obj.length) && x_obj.length <= 0) {
                return true;
            } else {
                for (var i in x_obj) {
                    return false;
                }
                return true;
            }
    }
    return false;
}

function SeiGetElement(x_elem) {
    if (typeof x_elem == 'string') {
        return document.all(x_elem);
    }
    return x_elem;
}

function SeiGetWindowSize() {
    if (document.documentElement && document.documentElement.clientWidth != 0) {
        return {
            width: document.documentElement.clientWidth,
            height: document.documentElement.clientHeight
        };
    } else {
        return {
            width: document.body.clientWidth,
            height: document.body.clientHeight
        };
    }
}

function SeiGetScrollPos() {
    return document.compatMode == 'CSS1Compat' ? {
        left: (document.documentElement.scrollLeft ? document.documentElement.scrollLeft :
            0),
        top: (document.documentElement.scrollTop ? document.documentElement.scrollTop :
            0)
    } : {
        left: (document.body.scrollLeft ? document.body.scrollLeft : 0),
        top: (document.body.scrollTop ? document.body.scrollTop : 0)
    };
}

function SeiGetPosition(x_element, x_scroll) {
    var p_x = 0,
        p_y = 0;
    if (x_scroll && x_element.style.position == 'absolute') {
        p_x += SeiGetScrollPos().left;
        p_y += SeiGetScrollPos().top;
    }
    while (x_element) {
        p_x += x_element.offsetLeft;
        p_y += x_element.offsetTop;
        if (x_scroll) {
            if (x_element.scrollLeft)
                p_x -= x_element.scrollLeft;
            if (x_element.scrollTop)
                p_y -= x_element.scrollTop;
        }
        x_element = x_element.offsetParent;
    }
    var p_ret = {
        x: p_x,
        y: p_y
    };
    return p_ret;
}

function SeiCheckOverlap(x_elem1, x_elem2) {
    var p_pos1 = SeiGetPosition(x_elem1, true);
    var p_pos2 = SeiGetPosition(x_elem2, true);
    return p_pos1.x <= p_pos2.x + x_elem2.offsetWidth &&
        p_pos1.y <= p_pos2.y + x_elem2.offsetHeight &&
        p_pos1.x + x_elem1.offsetWidth >= p_pos2.x &&
        p_pos1.y + x_elem1.offsetHeight >= p_pos2.y;
}

function SeiHideSelectOverDiv(x_divElem, x_form) {
    var p_length = x_form.elements.length,
        i, p_ret = [];
    for (i = 0; i < p_length; i++) {
        var p_elem = x_form.elements[i];
        if (p_elem.tagName != 'SELECT' || !SeiCheckOverlap(x_divElem, p_elem))
            continue;
        if (p_elem.style.visibility == 'hidden')
            continue;
        if (p_elem == document.activeElement)
            p_elem.blur();
        p_elem.style.visibility = 'hidden';
        p_ret.push(p_elem);
    }
    return p_ret;
}

function SeiDispSelectOverDiv(x_divElem, x_form, x_all) {
    var p_length = x_form.elements.length,
        i;
    if (arguments.length >= 3 && x_all) {
        for (i = 0; i < p_length; i++) {
            var p_elem = x_form.elements[i];
            if (p_elem.tagName != 'SELECT' ||
                p_elem.style.visibility != 'hidden')
                continue;
            p_elem.style.visibility = '';
        }
    }
    for (i = 0; i < p_length; i++) {
        var p_elem = x_form.elements[i];
        if (p_elem.tagName != 'SELECT' || p_elem.style.visibility != 'hidden' ||
            !SeiCheckOverlap(x_divElem, p_elem))
            continue;
        p_elem.style.visibility = '';
    }
}

function SeiSetOpacity(x_elem, x_opac) {
    if (x_opac == 1) {
        if (x_elem.style.filter != '')
            x_elem.style.filter = '';
    } else {
        x_elem.style.filter = 'Alpha(opacity=' + (x_opac * 100) + ')';
    }
}
var g_seiScrollErrPopup = null;
var g_seiScrollErrPopup2 = null;

function SeiAdjustScrollErrPopup(x_scrollDiv) {
    if (!g_seiScrollErrPopup)
        return




    g_seiScrollErrPopup2 = x_scrollDiv;
    var p_adjust = false,
        p_parent = g_seiScrollErrPopup.parentNode;
    while (p_parent) {
        if (p_parent == x_scrollDiv) {
            p_adjust = true;
            break;
        }
        p_parent = p_parent.parentNode;
    }
    if (!p_adjust)
        return;
    if (g_seiScrollErrPopup.style.display == 'none') {
        g_seiScrollErrPopup.style.display = '';
        SeiSetOpacity(g_seiScrollErrPopup, 0);
    }
    setTimeout(function() {
        SeiAdjustScrollErrPopup2(x_scrollDiv);
    }, 0);
}

function SeiAdjustScrollErrPopup2(x_scrollDiv) {
    if (!g_seiScrollErrPopup)
        return




    var p_pos0 = SeiGetPosition(g_seiScrollErrPopup),
        p_pos1 = SeiGetPosition(x_scrollDiv);
    var p_w0 = g_seiScrollErrPopup.offsetWidth;
    g_seiScrollErrPopup.style.display = 'none';
    if (p_pos0.x < p_pos1.x || p_pos0.x > p_pos1.x + x_scrollDiv.offsetWidth ||
        p_pos0.y < p_pos1.y ||
        p_pos0.y > p_pos1.y + x_scrollDiv.offsetHeight)
        return;
    g_seiScrollErrPopup.style.display = '';
    SeiSetOpacity(g_seiScrollErrPopup, 1);
}

function SeiEnableScrollErrPopup(x_errpopup, x_enable) {
    if (x_enable) {
        g_seiScrollErrPopup = x_errpopup;
        var p_parent = x_errpopup.parentNode,
            p_scrollDiv = null;
        while (p_parent) {
            if (!p_parent.style)
                break;
            if (p_parent.style.overflow == 'scroll' ||
                p_parent.style.overflowX == 'scroll' ||
                p_parent.style.overflowY == 'scroll') {
                p_scrollDiv = p_parent;
                break;
            }
            p_parent = p_parent.parentNode;
        }
        SeiSetOpacity(g_seiScrollErrPopup, 1);
        x_errpopup.style.display = '';
        x_errpopup.style.width = '';
        var p_pos0 = SeiGetPosition(x_errpopup),
            p_ws = SeiGetWindowSize(),
            p_sc = SeiGetScrollPos();
        if (p_pos0.x - p_sc.left + x_errpopup.offsetWidth >= p_ws.width) {
            x_errpopup.style.width = (p_ws.width - p_pos0.x + p_sc.left) + 'px';
        }
        if (p_scrollDiv) {
            setTimeout(function() {
                SeiAdjustScrollErrPopup2(p_scrollDiv);
            }, 0);
        }
        if (g_seiScrollErrPopup2)
            SeiAdjustScrollErrPopup(g_seiScrollErrPopup2);
    } else {
        g_seiScrollErrPopup = null;
        x_errpopup.style.display = 'none';
    }
}

function SeiCheckClassName(x_elem, x_class) {
    var p_elem = SeiGetElement(x_elem);
    if (!p_elem || !p_elem.className)
        return false
    var p_classes = p_elem.className.split(/\s+/);
    for (var i = 0; i < p_classes.length; i++) {
        if (p_classes[i] == x_class)
            return true;
    }
    return false;
}
var g_sei_placeholder = {};

function SeiAdjustScrollPlaceholder(x_scrollDiv) {
    if (!x_scrollDiv || g_sei_placeholder.length == 0) {
        return;
    }
    var p_list = g_sei_placeholder[x_scrollDiv.id]
    if (!p_list || p_list.length == 0) {
        return;
    }
    var p_pos = SeiGetPosition(x_scrollDiv);
    var p_barx = x_scrollDiv.offsetWidth - x_scrollDiv.clientWidth;
    var p_bary = x_scrollDiv.offsetHeight - x_scrollDiv.clientHeight;
    for (var i = 0; i < p_list.length; i++) {
        var p_input = p_list[i];
        var p_placeholder = document.getElementById(p_list[i].name +
            '_placeholder');
        p_input.parentNode.style.background = '';
        var p_pos2 = SeiGetPosition(p_input);
        var p_divY = p_pos.y;
        var p_divY2 = p_pos.y + x_scrollDiv.offsetHeight - p_bary / 2;
        var p_inputY = p_pos2.y - x_scrollDiv.scrollTop;
        var p_inputY2 = p_pos2.y + p_input.offsetHeight - x_scrollDiv.scrollTop;
        var p_divX = p_pos.x;
        var p_divX2 = p_pos.x + x_scrollDiv.offsetWidth - p_barx / 2;
        var p_inputX = p_pos2.x - x_scrollDiv.scrollLeft;
        var p_inputX2 = p_pos2.x + p_input.offsetWidth - x_scrollDiv.scrollLeft;
        if (p_divY > p_inputY || p_divY2 < p_inputY2 || p_divX > p_inputX ||
            p_divX2 < p_inputX2) {
            p_placeholder.parentNode.style.display = 'none';
        } else {
            p_placeholder.parentNode.style.display = '';
        }
    }
}

function SeiAddPlaceholder(x_elem) {
    if (!x_elem) {
        return;
    }
    var p_table = x_elem;
    while (p_table) {
        if (p_table.tagName == 'TABLE') {
            break;
        }
        p_table = p_table.parentNode;
    }
    if (p_table && p_table.parentNode && p_table.parentNode.tagName == 'DIV' &&
        p_table.parentNode.id) {
        var p_list = g_sei_placeholder[p_table.parentNode.id]
        if (!p_list) {
            p_list = new Array();
        }
        p_list.push(x_elem);
        g_sei_placeholder[p_table.parentNode.id] = p_list;
    }
}

function SeiFocusPrev(x_element) {
    if (!x_element.form)
        return false;
    var i, j, p_func;
    var p_elements = x_element.form.elements;
    var p_length = x_element.form.elements.length;
    if (x_element.tabIndex && x_element.tabIndex > 0) {
        var p_nextTabIndex = -1,
            p_nextTabElem = null;
        for (i = p_length - 1; i >= 0; i--) {
            if (p_elements[i] != x_element)
                continue;
            for (j = i - 1; j >= 0; j--) {
                if (p_elements[j].name != x_element.name &&
                    p_elements[j].tabIndex == x_element.tabIndex) {
                    p_nextTabElem = p_elements[j];
                    break;
                }
            }
            if (p_nextTabElem)
                break;
            for (j = p_length - 1; j >= 0; j--) {
                if (j == i || !p_elements[j].tabIndex ||
                    p_elements[j].tabIndex >= x_element.tabIndex)
                    continue
                if (p_nextTabIndex < 0 ||
                    p_elements[j].tabIndex > p_nextTabIndex) {
                    p_nextTabElem = p_elements[j];
                    p_nextTabIndex = p_elements[j].tabIndex
                }
            }
            if (p_nextTabElem)
                break;
            for (j = i - 1; j >= 0; j--) {
                if (p_elements[j].tabIndex > 0 ||
                    p_elements[j].type == 'hidden')
                    continue;
                if (SeiFocusField(p_elements[j]))
                    return true;
            }
            break;
        }
        if (p_nextTabElem && SeiFocusField(p_nextTabElem))
            return true;
    }
    p_func = 'sei' + SeiFormName(x_element.form) + 'FocusPrev';
    if (eval('typeof(' + p_func + ')') == 'function') {
        var p_fieldname = x_element.name;
        if (x_element.type == 'radio' || x_element.type == 'checkbox') {
            if (x_element.form.elements[x_element.name].length > 0) {
                p_fieldname += '[0]';
            }
        }
        if (eval(p_func + '(\'' + p_fieldname + '\')')) {
            return true;
        }
    }
    for (i = p_length - 1; i >= 0; i--) {
        if (p_elements[i] != x_element) {
            continue;
        }
        for (j = i - 1; j >= 0; j--) {
            if (p_elements[j].name == x_element.name) {
                continue;
            }
            var p_parent = p_elements[j].parentNode;
            if (p_parent && p_parent.className == 'SeiTimeSelItem' &&
                x_element.name) {
                var p_idx = p_elements[j].name.indexOf('pms_mi_');
                if (p_idx >= 0 &&
                    !SeiFocusField(document.all(p_elements[j].name
                        .substring(0, p_idx) +
                        'pms_hh_' +
                        p_elements[j].name.substring(p_idx + 7)))) {
                    continue;
                }
            }
            if (!SeiFocusField(p_elements[j])) {
                continue;
            }
            return false;
        }
        for (j = p_length - 1; j > i; j--) {
            if (p_elements[j].name == x_element.name ||
                !SeiFocusField(p_elements[j])) {
                continue;
            }
            return false;
        }
    }
    return true;
}

function SeiFocusUp(x_element, x_nextfield) {
    var p_next = x_element.form.elements[x_nextfield];
    if (p_next) {
        return SeiFocusField(p_next);
    } else {
        return SeiFocusPrev(x_element);
    }
}

function SeiFocusDown(x_element, x_nextfield) {
    var p_next = x_element.form.elements[x_nextfield];
    if (p_next) {
        return SeiFocusField(p_next);
    } else {
        return SeiFocusNext(x_element);
    }
}

function SeiCheckedIndex(x_elements, x_check) {
    if (x_elements.length) {
        var i;
        for (i = 0; i < x_elements.length; i++) {
            if (x_elements[i].checked) {
                return i;
            }
        }
        if (x_check) {
            x_elements[0].checked = true;
        }
    } else {
        if (x_check) {
            x_elements.checked = true;
        }
    }
    return -1;
}
var g_seiHandlerField = null;
var g_seiKeyHandlerFunc = [];
var SeiKey = {
    isEnterKeyUsed: function(x_ev) {
        return true;
    },
    isFocusNext: function(x_ev) {
        return !event.shiftKey && !event.ctrlKey && !event.altKey &&
            event.keyCode == 13;
    },
    isFocusPrev: function(x_ev) {
        return event.shiftKey && !event.ctrlKey && !event.altKey &&
            event.keyCode == 13;
    },
    isFocusUp: function(x_ev) {
    	return !event.shiftKey && !event.ctrlKey && !event.altKey &&
    		event.keyCode == 38;
    },
    isFocusDown: function(x_ev) {
    	return !event.shiftKey && !event.ctrlKey && !event.altKey &&
			event.keyCode == 40;
    },
    isFocusNextError: function(x_ev) {
        return !event.shiftKey && event.ctrlKey && !event.altKey &&
            event.keyCode == 13;
    },
    isFocusPrevError: function(x_ev) {
        return event.shiftKey && event.ctrlKey && !event.altKey &&
            event.keyCode == 13;
    },
    isFocusPrevLineTop: function(x_ev) {
        return !event.shiftKey && event.ctrlKey && !event.altKey &&
            event.keyCode == 38;
    },
    isFocusNextLineTop: function(x_ev) {
        return !event.shiftKey && event.ctrlKey && !event.altKey &&
            event.keyCode == 40;
    },
    isSelectNextItem: function(x_ev) {
        return !event.shiftKey && !event.ctrlKey && !event.altKey &&
            event.keyCode == 39;
    },
    isSelectPrevItem: function(x_ev) {
        return !event.shiftKey && !event.ctrlKey && !event.altKey &&
            event.keyCode == 37;
    },
    isInputArrayDelete: function(x_ev) {
        return false;
    },
    isOpenPopupWin: function(x_ev) {
        return !event.shiftKey && !event.ctrlKey && !event.altKey &&
            event.keyCode == 120;
    },
    isClosePopupWin: function(x_ev) {
        return !event.shiftKey && !event.ctrlKey && !event.altKey &&
            event.keyCode == 115;
    },
    isSelectListNext: function(x_ev) {
        return !event.shiftKey && !event.ctrlKey && !event.altKey &&
            event.keyCode == 40;
    },
    isSelectListPrev: function(x_ev) {
        return !event.shiftKey && !event.ctrlKey && !event.altKey &&
            event.keyCode == 38;
    },
    isSelectListReturn: function(x_ev) {
        return !event.shiftKey && !event.ctrlKey && !event.altKey &&
            event.keyCode == 13;
    },
    isSubmitForm: function(x_ev) {
        return !event.shiftKey && !event.ctrlKey && !event.altKey &&
            event.keyCode == 123;
    },
    isResetForm: function(x_ev) {
    	return false;
    },
    isSubmenuNextData: function(x_ev) {
        return !event.shiftKey && event.ctrlKey && !event.altKey &&
            event.keyCode == 68;
    },
    isSubmenuPrevData: function(x_ev) {
        return !event.shiftKey && event.ctrlKey && !event.altKey &&
            event.keyCode == 85;
    },
    isSubmenuBackList: function(x_ev) {
        return !event.shiftKey && !event.ctrlKey && !event.altKey &&
            event.keyCode == 119;
    },
    isPagerNextPage: function(x_ev) {
        return !event.shiftKey && event.ctrlKey && !event.altKey &&
            event.keyCode == 78;
    },
    isPagerLastPage: function(x_ev) {
        return !event.shiftKey && event.ctrlKey && !event.altKey &&
            event.keyCode == 69;
    },
    isPagerPrevPage: function(x_ev) {
        return !event.shiftKey && event.ctrlKey && !event.altKey &&
            event.keyCode == 80;
    },
    isPagerFirstPage: function(x_ev) {
        return !event.shiftKey && event.ctrlKey && !event.altKey &&
            event.keyCode == 84;
    },
    isClear: function(x_ev) {
        return !event.shiftKey && event.ctrlKey && !event.altKey &&
            event.keyCode == 76;
    },
    isComplementDate: function(x_ev) {
        return !event.shiftKey && event.ctrlKey && !event.altKey &&
            event.keyCode == 32;
    },
    isCurrentDate: function(x_ev) {
        return !event.shiftKey && event.ctrlKey && !event.altKey &&
            event.keyCode == 187;
    },
    isCurrentTime: function(x_ev) {
        return !event.shiftKey && event.ctrlKey && !event.altKey &&
            event.keyCode == 186;
    },
    isF1KeyUsed: function(x_ev) {
        return false;
    },
    isF2KeyUsed: function(x_ev) {
        return false;
    },
    isF3KeyUsed: function(x_ev) {
        return false;
    },
    isF4KeyUsed: function(x_ev) {
        return true;
    },
    isF5KeyUsed: function(x_ev) {
        return false;
    },
    isF6KeyUsed: function(x_ev) {
        return false;
    },
    isF7KeyUsed: function(x_ev) {
        return false;
    },
    isF8KeyUsed: function(x_ev) {
        return true;
    },
    isF9KeyUsed: function(x_ev) {
        return true;
    },
    isF10KeyUsed: function(x_ev) {
        return false;
    },
    isF11KeyUsed: function(x_ev) {
        return false;
    },
    // isF12KeyUsed : function(x_ev){return event.keyCode == 123;}
    isF12KeyUsed: function(x_ev) {
        return false;
    }
};

function SeiKeyHandler(x_ev) {
    var p_form = null;
    if (document.forms && document.forms.length > 0) {
        p_form = document.forms[0];
    }
    for (var i = 0; i < g_seiKeyHandlerFunc.length; i++) {
        if (!g_seiKeyHandlerFunc[i](x_ev, p_form))
            return false;
    }
    if (SeiKey.isSubmitForm(x_ev) && p_form) {
        var i, p_len = p_form.length,
            p_submit = null;
        for (i = 0; i < p_len; i++) {
            if (p_form.elements[i].type == 'submit') {
                p_submit = p_form.elements[i];
                break;
            }
        }
        if (p_submit) {
            if (event.srcElement.onblur || event.srcElement.onchange) {
                p_submit.focus();
                g_seiHandlerField = event.srcElement.name;
            }
            if (event.srcElement.onblur) {
                event.srcElement.onblur();
                g_seiHandlerField = event.srcElement.name;
            }
            p_submit.click();
        }
        event.keyCode = 0;
        return false;
    }
    if (SeiKey.isResetForm(x_ev) && p_form) {
        var p_reset = p_form.elements['sei_reset'];
        if (p_reset)
            (p_reset.length && p_reset.length >= 2 ? p_reset[0] : p_reset)
            .click();
        SeiCancelKeyEvent(x_ev);
        return false;
    }
    if (SeiKey.isSubmenuNextData(x_ev)) {
        if (typeof pms_data_sei_onSubMenu == 'function' &&
            pms_data_sei_onSubMenu()) {
            if (typeof go_next == 'function') {
                go_next();
            }
            return false;
        }
        return false;
    }
    if (SeiKey.isSubmenuPrevData(x_ev)) {
        if (typeof pms_data_sei_onSubMenu == 'function' &&
            pms_data_sei_onSubMenu()) {
            if (typeof go_prev == 'function') {
                go_prev();
            }
            return false;
        }
        return false;
    }
    // thien
    if (SeiKey.isSubmenuBackList(x_ev)) {
        if (typeof pms_data_sei_onSubMenu == 'function' &&
            pms_data_sei_onSubMenu()) {
            if (typeof pms_back_list == 'function') {
                pms_back_list();
            }
            return false;
        }
        return false;
    }
    if (p_form && p_form.page) {
        var p_func = null;
        if (SeiKey.isPagerNextPage(x_ev) || SeiKey.isPagerLastPage(x_ev) ||
            SeiKey.isPagerPrevPage(x_ev) ||
            SeiKey.isPagerFirstPage(x_ev)) {
            p_func = 'Sei' + SeiFormName(p_form) + '_paging';
        }
        if (SeiKey.isPagerNextPage(x_ev) && eval(p_func)) {
            var p_page = parseInt(p_form.page.value) + 1;
            eval(p_func + '(' + p_page + ')');
            return false;
        }
        if (SeiKey.isPagerLastPage(x_ev) && eval(p_func)) {
            eval(p_func + '(2147483647)');
            return false;
        }
        if (SeiKey.isPagerPrevPage(x_ev) && eval(p_func)) {
            var p_page = parseInt(p_form.page.value) - 1;
            if (p_page > 0) {
                eval(p_func + '(' + p_page + ')');
            }
            return false;
        }
        if (SeiKey.isPagerFirstPage(x_ev) && eval(p_func)) {
            eval(p_func + '(1)');
            return false;
        }
    }
    if (SeiKey.isClear(x_ev)) {
        var p_form = event.srcElement.form;
        if (!p_form) {
            if (document.forms && document.forms.length > 0) {
                p_form = document.forms[0];
            }
        }
        if (p_form) {
            for (var i = 0; i < p_form.length; i++) {
                if (p_form.elements[i].name == 'sei_clear') {;
                    p_form.elements[i].click();
                    break;
                }
            }
        }
        SeiCancelKeyEvent(x_ev);
        return false;
    }
    if (SeiKey.isClosePopupWin(x_ev)) {
        top.window.close();
        SeiCancelKeyEvent(x_ev);
        return false;
    }
    if (event.keyCode == 13 && !event.srcElement.type &&
        event.srcElement.contentEditable != 'true' &&
        event.srcElement.tagName != 'A' && SeiKey.isEnterKeyUsed(event)) {
        return false;
    }
    if (SeiCheckLimitHistory(x_ev)) {
        alert('その操作は禁止されています。');
        return false;
    }
    return true;
}

function SeiKeyHandler2(x_ev) {
    if (event.keyCode == 13 && event.srcElement.type == 'text' &&
        SeiKey.isEnterKeyUsed(event)) {
        return false;
    }
    return true;
}

var g_seiLimitBS = false;

function SeiCheckLimitHistory(x_ev) {
    if (!event.shiftKey && !event.ctrlKey && event.altKey &&
        event.keyCode == 37) {
        return true;
    } else if (!(!event.shiftKey && !event.ctrlKey && !event.altKey && event.keyCode == 8)) {
        return false;
    }
    var p_target = event.srcElement;
    var p_parentid;
    if (p_target.parentNode) {
        p_parentid = p_target.parentNode.id;
    }
    if (document.selection.type == 'Text' && p_parentid == 'editArea') {
        return false;
    }
    if (document.selection.type == 'Control' &&
        (p_target.id == 'SeiHeader' || p_parentid == 'editArea')) {
        return true;
    }
    if (p_parentid &&
        (document.selection.type == 'None' && p_parentid == 'editArea' || p_parentid != 'SeiHeader' &&
            p_parentid.indexOf('SeiHeader') == 0)) {
        return false;
    }
    if (p_target.tagName == 'TEXTAREA' ||
        p_target.tagName == 'INPUT' &&
        (p_target.type == 'text' || p_target.type == 'password' || p_target.type == 'file' &&
            !g_seiLimitBS)) {
        return false;
    }
    return true;
}

this.focus();
var g_pageno = 396847;
var g_sessno = 5;
var g_seiSubmit = false;
var g_waitSubmit = 0;
var g_submitAfterWait = true;
var g_seiAfterSubmitJS = null;
var g_seiWinField = 0;
var g_seiAcceptEditWarn = false;

function SeiJump(x_target, x_url) {
    if (x_url.indexOf('rak_savesql') < 0 && document.forms.length >= 1) {
        var p_idx = x_url.indexOf('#'),
            p_anc = '';
        if (p_idx > 0) {
            p_anc = x_url.substring(p_idx);
            x_url = x_url.substring(0, p_idx);
        }
        x_url += (x_url.indexOf('?') < 0 ? '?' : '&');
        x_url += 'rak_savesql=' +
            (document.forms[0].elements['rak_savesql'] ? document.forms[0].elements['rak_savesql'].value :
                '');
        x_url += p_anc;
    }
    x_target.location.href = x_url;
}

/**
 * Convert SeiDate
 */
function SeiToday() {
    return new Date()
}

var g_update_d;

function SeiDate_update_d(x_field, x_ev) {
    var p_value = x_field.value;
    var p_date = SeiParseDate(p_value);
    if (p_date == null)
        return false;
    g_update_d = x_field.value;
    p_date = SeiEditDate(p_date);
    if (x_field.value != p_date)
        x_field.value = p_date;
    return true;
}

function SeiRmDate_update_d(x_field, x_ev) {
    if (!g_update_d) {
        return false;
    }
    if (x_field.value != g_update_d) {
        x_field.value = g_update_d;
    }
    g_update_d = null;
    x_field.focus();
    return true;
}

function SeiSetToday_update_d(x_field, x_ev) {
    var p_date = SeiEditDate(new Date());
    x_field.value = p_date;
    return SeiDate_update_d(x_field, x_ev);
}

function seiFocusNextError(x_field) {
	var idFocusList = new Array();
		 idFocusList = listStrIdFocus.split(",");
	for(var i = 0 ;i < idFocusList.length;i++){
		if (idFocusList[i] == x_field){
			for(var j = i;j < idFocusList.length - 1;j++){
				for(var k = 0;k < errorObjectList.length;k++){
					if(idFocusList[j + 1] == errorObjectList[k].errorId){
						SeiFocusNext(document.getElementById(idFocusList[j]));
						return;
					}
				}
			}
		}
	}
}

function seiFocusPrevError(x_field) {
	var idFocusList = new Array();
	 idFocusList = listStrIdFocus.split(",");
	 for(var i = idFocusList.length - 1 ;i >= 0;i--){
		 if (idFocusList[i] == x_field){
			 for(var j = i;j >= 1;j--){
				 for(var k = 0;k < errorObjectList.length;k++){
					 if(idFocusList[j - 1] == errorObjectList[k].errorId){
						 SeiFocusPrev(document.getElementById(idFocusList[j]));
					return;
				}
			}
		}
	}
}
}

function loadSeiDate() {
    $('.seiDate').each(
        function() {
            $(".seiDate").attr("onfocus",
                "SeiRmDate_update_d(this,event);this.select()");
            $(".seiDate").attr("onblur", "SeiDate_update_d(this,event)");
        });
}

/*
 * END Convert SeiDate
 */

function SeiRmComma_supplier_price(x_target, x_ev) {
    var p_value = x_target.value;
    if (p_value == '') {
        return false;
    }
    p_value = SeiRemoveComma(p_value);
    if (x_target.value != p_value) {
        x_target.value = p_value;
    }
}

function SeiComma_supplier_price(x_target, x_ev) {
    var p_value = x_target.value;
    if (p_value == '') {
        return false;
    }
    p_value = SeiEditComma(p_value);
    if (p_value == null || p_value == '')
        return;
    x_target.value = p_value;
}

function loadSeiPrice() {
    $('.seiPrice').each(
        function() {
            $(".seiPrice").attr("onfocus",
                "SeiRmComma_supplier_price(this,event);");
            $(".seiPrice").attr("onblur",
                "SeiComma_supplier_price(this,event)");
        });
}

/*--------------------------------------
 * Render Japan Date Type
 * */
var listDating_main = [{
    datingName: "平成",
    startYear: 1989,
    endYear: null
  },
  {
    datingName: "昭和",
    startYear: 1926,
    endYear: 1988
  }
];

$(document).ready(function() {
renderDate();
});

function renderDate(){
  renderDateType(true);
  renderDateType(false);
}
function renderDateType(flag) {
  var id = flag == false? '.jpDate-1': '.jpDate-2';
  var date_list = listDating_main;
  var data = GenYearByDating(date_list, flag);
  for (i = 0; i < data.length; i++) {
    var x = document.createElement("option");
    x.setAttribute("value", data[i].substring(0, 4));
    var t = document.createTextNode(data[i]);
    x.appendChild(t);
    $(id).append(x);
  }

}

function GenYearByDating(listDating, isVehicaleCheck) {
  var data = [];
  for (i = 0; i < listDating.length; i++) {
    GenYearByEachDating(listDating[i], isVehicaleCheck, data);
  }
  data.sort();
  data.reverse();
  return data;
}

function GenYearByEachDating(datingObject, isVehicaleCheck, yearList) {
  var endDate = datingObject.endYear;
  if (datingObject.endYear == null)
    endDate = isVehicaleCheck == true ? new Date().getFullYear() + 5 : new Date().getFullYear();
  var currentYear = datingObject.startYear < 1960 ? 1960 : datingObject.startYear;
  var i = currentYear - datingObject.startYear + 1;
  while (currentYear <= endDate) {
    yearList.push(currentYear++ + "年/" + datingObject.datingName + i++ + "年");
  }
}
/*
 * [End] Render Japan Date Type --------------------------------------
 */

function lockResize() {
    $(window).resize(function() {
        window.resizeTo(size[0], size[1]);
        window.focus();
    });
}

function SeikeySelected(event, x_field) {
    
    if (SeiKey.isFocusPrevError(event)) {
        seiFocusPrevError(x_field.id);
        return false;
    } else if (SeiKey.isFocusNextError(event)) {
        seiFocusNextError(x_field.id);
        return false;
    }
    if (SeiKey.isFocusPrev(event)) {
        SeiFocusPrev(x_field);
        return false;
    } else if (SeiKey.isFocusNext(event)) {
        SeiFocusNext(x_field);
        return false;
    } else if (SeiKey.isFocusDown(event)) {
        SeiFocusNext(x_field);
        return false;
    } else if (SeiKey.isFocusUp(event)) {
        SeiFocusPrev(x_field);
        return false;
    } else if (SeiKey.isPagerNextPage(event)) {
        SeiPagerNextPage();
        return false;
    } else if (SeiKey.isPagerLastPage(event)) {
        SeiPagerLastPage();
        return false;
    } else if (SeiKey.isPagerPrevPage(event)) {
        SeiPagerPrevPage();
        return false;
    } else if (SeiKey.isPagerFirstPage(event)) {
        SeiPagerFirstPage();
        return false;
    } else if (SeiKey.isSelectPrevItem(event)) {
        var i = SeiIndexOf(document.makerForm.x_field.id, x_field);
        if (i > 0) {
            document.makerForm.x_field.id[i - 1].focus();
            return false;
        }
    } else if (SeiKey.isSelectNextItem(event)) {
        var i = SeiIndexOf(document.makerForm.x_field.id, x_field);
        if (i >= 0 && i + 1 < document.makerForm.x_field.id.length) {
            document.makerForm.x_field.id[i + 1].focus();
            return false;
        }
    }
    
    return true;
}

/**
 * クリアボタン押下
 * @param p クッリクしたボタン
 * @returns
 */
function clearInputTau(p) {
    var formId = $(p.form);
    var textInput = 'input:not([type="button"], [type="submit"], [type="submit"], [type="checkbox"])';
    var checkboxInput = 'input[type="checkbox"]';
    var radioInput = 'input[type="radio"]';
    var select = 'select';
    var mutilselect = 'select[multiple="multiple"]';
    $(p.form).find(textInput).each(function() {
        if (!$(this).hasClass('notClear')) {
            $(this).val('');
        }
    });
    $(p.form).find(checkboxInput).each(function() {
        if (!$(this).hasClass('notClear')) {
            $(this).prop("checked", false);
        }
    });
    $(p.form).find(radioInput).each(function() {
        if (!$(this).hasClass('notClear')) {
            $(this).prop("checked", false);
        }
    });
    $(p.form).find(select).each(function() {
        if (!$(this).hasClass('notClear')) {
            $(this).prop('selectedIndex', 0);
        }
    });
    $(p.form).find(mutilselect).each(function() {
        if (!$(this).hasClass('notClear')) {
            $(this).find('option').remove();
        }
    });
}


var global_id = [];

function getDatalist(x, type){
	var listDataId = 'data' + x.id;
	var listData = $('#'+listDataId).attr('value');
	var arr = listData.substring(1,listData.length-1).split(", ");
	autocompleteTau(x, arr, type);
}

function loadAutoComplete() {
            $(".autocomplete-1").attr("onkeyup", " getDatalist(this, 1);");
            $(".autocomplete-2").attr("onkeyup", " getDatalist(this, 2);");
            $(".autocomplete-3").attr("onkeyup", " getDatalist(this, 3);");
            $(".autocomplete-4").attr("onkeyup", " getDatalist(this, 4);");
}

/**
 * autocomplete data
 * @param x
 * @param arr
 * @param type
 * @returns
 */
function autocompleteTau(x, arr, type) {
    var aCleanData = arr;
    $('#' + x.id).autocomplete({
        source: function(request, response) {
        	console.log(1);
			var sValue = request.term;
			var aSearch = [];
			sValue = me.libs.katakanaToHiragana(sValue);
        	// for each element in the main array ...
            $(aCleanData).each(function(iIndex, targetElement) {
            	var sElement = me.libs.katakanaToHiragana(targetElement);
                switch (type) {
                    case 1: // left to right
                        if (sElement.substr(0, sValue.length).trim() == sValue) {
                            aSearch.push(targetElement);
                        }
                        break;
                    case 2: // right to left
                        if (sElement.substring(sElement.length, (sElement.length - sValue.length)) == sValue)
                            aSearch.push(targetElement);
                        break;
                    case 3: // equal
                        if (sValue == sElement)
                            aSearch.push(targetElement);
                        break;
                    case 4: // contain
                        if (sValue.length <= sElement.length && sElement.indexOf(sValue) >= 0)
                            aSearch.push(targetElement);
                        break;
                    default:
                        break;
                }
            });
            response(aSearch);
        }
    });
}

function blockKey() {
    $(document).keydown(function(event) {
        if(event.keyCode == 84 && event.ctrlKey){
            return false;
        } else if(event.keyCode == 123) {
            return false;
        } else if((event.keyCode == 37 && event.altKey) || (event.keyCode == 39 && event.altKey)){
            return false;
        } else if (event.ctrlKey && event.shiftKey && event.keyCode == 73) {
            return false;
        } else if (event.ctrlKey && event.keyCode == 76) {
            return false;
        } else if (event.ctrlKey && event.keyCode == 68) {
            return false;
        } else if (event.ctrlKey && event.keyCode == 85) {
            return false;
        } else if (event.ctrlKey && event.keyCode == 80) {
            return false;
        } 
        else if (event.ctrlKey && event.keyCode == 84) {
            return false;
        } 
    });
}


function clearAllInputTau(){
	$('form').each(function(){
		var textInput = $(this).attr('class') + ' input:not([type="button"], [type="reset"], [type="hidden"], [type="submit"], [type="checkbox"])';
	    var checkboxInput = $(this).attr('class') + ' input[type="checkbox"]';
	    $('.' + textInput).val('');
	    $('.' + checkboxInput).each(function() {
	        $(this).prop("checked", false);
	    });
	});
}

function setActionRedirect(idForm,method,action) {
	var form = $(idForm);
	form.attr('action', action);
	form.attr('method', method);
	form.submit();
}

function doRegisterCSV(id) {
    var form = $('#'+id);
    var url = form.attr('action');
    var data = form.serialize();
    $.ajax({
         url: url,
         method : "POST",
         data:data,
         dataType:"json",
         success: function(data) {
             if(data.status) {
                 // Show message success
                 $('#MessageInfo').text(data.message);
                 $('#MessageInfo').removeClass('SeiError');
             } else {
              // Show message error
                 $('.SeiError').text(data.message);
             }
         },
         error: function(xhr) {
             console.log('ERROR');
         }
        });
}

function uploadFileCSV(){
    if($("#upfile").val() !='') {
        $("#readCSVFrm").submit();
    }
}

/** HADV */
function _doRedirect(action, method, data, options) {
	var form = $('<form method="'+method+'"/>').attr('action', action).hide();
	Object.keys(data || {}).map(function(v) {
		form.append($('<input type="hidden" name="'+v+'" value="'+data[v]+'"/>'));
	})
	if(options && options.useDataFromForm) {
		$(options.useDataFromForm).serializeArray().map(function(v){
			if((options.ignore || []).indexOf(v.name) == -1) {
				form.append($('<input type="hidden" name="'+v.name+'" value="'+v.value+'"/>'));
			}
		})
	}
	form.appendTo('body').submit();
}

/** HADV */
function doGetRedirect(action, data) {
	_doRedirect(action, 'get', data);
}

/** HADV */
function doPostRedirect(action, data, options) {
	_doRedirect(action, 'post', data, options);
}

/** HADV */
function popupWindow(url, options) {
	options = $.extend(options || {}, {
		width: 800,
		height: 600,
		title: 'Popup',
		scrollbars: 'yes',
		center: true
	})
	
	if(options.center) {
		var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
	    var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;
	
	    var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
	    var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;
	
	    options.left = ((width / 2) - (options.width / 2)) + dualScreenLeft;
	    options.top =  ((height / 2) - (options.height / 2)) + dualScreenTop;
	}
    
    var windowConfig = Object.keys(options).map(function(c){
		return [c, '=', options[c]].join('');
	}).join(',');
    
    var newWindow = window.open(url, options.title, windowConfig);

    // Focus on the newWindow
    if (window.focus) {
        newWindow.focus();
    }
}

/** HADV */
function loadMasterDataForSelectBox(boxSelector, endpoint, query, options) {
	var options = $.extend({
		defaultOption: true
	}, options || {});
	if(options.defaultOption) {
		$(boxSelector).html($('<option value=""/>').text('（選択して下さい）'));
	} else {
		$(boxSelector).html('');
	}
	$.ajax({
		type: 'POST',
		url: endpoint,
		data: query,
		success: function(resp) {
			(resp || []).map(function(v){
				$(boxSelector).append($('<option/>').attr('value', v.code).text(v.name));
			})
		}
	});
}

/* create post form element */
function createPostFormSubmit(action) {
    var form = document.createElement('form');
    form.name = "formRedirect";
    form.action = action;
    form.method = 'POST';
    
    document.body.appendChild(form);
    
    return form;
}

/* MinhLS create hidden input */
function createHiddenInput(form, name, value) {
    var input = document.createElement('input');
    input.type = 'hidden';
    input.name = name;
    input.value = value;
    form.appendChild(input);
}

// MinhLS
function doRedirect(action, name, val) {
    var form = createPostFormSubmit(action);
    createHiddenInput(form, name, val);
    form.submit();
}

/**
 * Hien thi popup khi click vao button 参考
 * 
 * @param elemId
 * @param data
 * @param callback
 * @param preFunction
 * @param callBackShow
 * @returns
 */
function showPopupByRef(elemId, data, rowClickCallback, preSearchFn, afSearchFn) {
	// thuc hien set data cho popup
	if(isFunction(preSearchFn)) {
		preSearchFn(elemId, data);
	} else {
		loadDataForPopup(elemId, data);
	}
	// thuc hien x ly show popup
	showPopup(elemId, data, rowClickCallback, false, afSearchFn);
}

/**
 * Load data cho form truoc khi thuc hien tim kiem
 * 
 * @param elemId
 * @param data
 * @returns
 */
function loadDataForPopup(elemId, data) {
	if(isUnset(data) || isUnset(elemId)) {
		return;
	}
}
/**
 * Hien thi popup khi lost focus
 * 
 * @param elemId
 * @param data
 * @param rowClickCallback
 * @param preSearchFn
 * @param afSearchFn
 * @returns
 */
function showPopupByLostFocus(elemId, data, rowClickCallback, preSearchFn, afSearchFn) {
	// thuc hien set data cho popup
	if(isFunction(preSearchFn)) {
		preSearchFn(elemId, data);
	} else {
		loadDataForPopup(elemId, data);
	}
	// thuc hien x ly show popup
	showPopup(elemId, data, rowClickCallback, true, afSearchFn);
}

// hien thi man hinh popup
function showPopup(elemId, data, callback, isLostFocus, callBackShow) {
	if(isLostFocus) {
		$('#' + elemId).hasClass('hidden')
		$('#' + elemId).removeClass('hidden');
	} else {
		// get id cua popup
		var id = '#' + elemId;
		var url = $(id).attr('action');
		api.ajaxPopup(url, data, function(responseData) {
			// kiem tra neu ket qua tra ve la 0
			if(responseData.totalRowCount == 0) {
				if(isFunction(callback)) {
					callback(null);
				}
			} else if(responseData.totalRowCount == 1) {
				if(isFunction(callback)) {
					// callback data tra ve
					callback(responseData.dtoList[0]);
				}
				// binding data for row
			} else if(responseData.totalRowCount > 1) {
				// create html element
				$('#' + elemId).showPopup(responseData.dtoList, responseData, function(_data) {
					// callback khi click vao row
					if(isFunction(callback)) {
						callback(_data);
					}
				});
				$('#' + elemId).hasClass('hidden')
				$('#' + elemId).removeClass('hidden');
			}
			if(isFunction(callBackShow)) {
				callBackShow(responseData);
			}
		});
	}
}
// click vao ket qua tim kiem duoc
function selectRow(tr, callBack) {
	if(isUnset(tr)) {
		return;
	}
	if(!isFunction(callBack)) {
		return;
	}
	var result = {};
	// get danh sach tds
	var tds = $(tr).find('td');
	for(var i = 0; i < tds.length; i++) {
		var td = tds[i];
		var name = $(td).attr('name');
		if(isSet(name)) {
			result[name] = $(td).text();
		}
	}
	callBack(result);
}

// kiem tra doi tuong !null and !undefined
function isSet(testObject) {
	return typeof testObject !== 'undefined' && testObject != null;
}
// kiem tra doi tuong null or undefined
function isUnset(testObject) {
	return typeof testObject === 'undefined' || testObject == null;
}
/**
 * kiem tra mot doi tuong la function
 * 
 * @param testObject
 * @returns
 */
function isFunction(testObject) {
	return typeof testObject === 'function';
};

function handlerMakerModelRowSelect(selectedData) {
	$('#mkNm').val(selectedData['mkNm']);
	$('#mkCd').val(selectedData['mkCd']);
	$('#mdNm').val(selectedData['mdNm']);
	$('#mdCd').val(selectedData['mdCd']);
}

function handlerMakerModelGradeRowSelect(selectedData){
	$('#mkNm').val(selectedData['mkNm']);
	$('#mkCd').val(selectedData['mkCd']);
	$('#mdNm').val(selectedData['mdNm']);
	$('#mdCd').val(selectedData['mdCd']);
	$('#gdNm').val(selectedData['gdNm']);
	$('#gdCd').val(selectedData['gdCd']);
}

function handlerPartsRowSelect (selectedData){
	$('#stkNo').val(selectedData['stkNo']);
	$('#ptsProdNmByLg').val(selectedData['ptsProdNmByLg']);
	$('#mkNm').val(selectedData['mkNm']);
	$('#mkCd').val(selectedData['mkCd']);
	$('#mdNm').val(selectedData['mdNm']);
	$('#mdCd').val(selectedData['mdCd']);
	$('#salesPrice').val(selectedData['salesPrice']);
	$('#supplierPrice').val(selectedData['supplierPrice']);
}

function handlerPartsCatalogRowSelect(selectedData){
	$('#catNo').val(selectedData['catNo']);
	$('#stkNo').val(selectedData['stkNo']);
	$('#ptsProdNmByLg').val(selectedData['ptsProdNmByLg']);
	$('#mkNm').val(selectedData['mkNm']);
	$('#mdNm').val(selectedData['mdNm']);
	$('#ptsPrice').val(selectedData['ptsPrice']);
}

function handlerCustomerRowSelect(selectedData){
	$('#memId').val(selectedData['memId']);
	$('#memNm').val(selectedData['memNm']);
	$('#memEmlAddr').val(selectedData['memEmlAddr']);
}

function handlerRouteRowSelect(selectedData){
	$('#voyId').val(selectedData['voyId']);
	$('#voyNo').val(selectedData['voyNo']);
	$('#vesCoCd').val(selectedData['vesCoCd']);
	$('#vesCoNm').val(selectedData['vesCoNm']);
	$('#vesCd').val(selectedData['vesCd']);
	$('#vesNm').val(selectedData['vesNm']);
}

function handlerDeliveryCostSelect(){
	$('#categoryName').val("Car");
	$('#quantity').val("5");
	$('#deliveryCost').val("500");
	$('#consumptionTax').val("10");
}

function handlerShipCompanyRowSelect(selectedData){
	$('#vesCoNm').val(selectedData['vesCoNm']);
	$('#vesCoCd').val(selectedData['vesCoCd']);
}

function handlerShipRowSelect(selectedData){
	$('#vesCd').val(selectedData['vesCd']);
	$('#vesNm').val(selectedData['vesNm']);
	$('#vesCoCd').val(selectedData['vesCoCd']);
	$('#vesCoNm').val(selectedData['vesCoNm']);
}

function handlerINVOICECommentRowSelect(selectedData){
	$('#univMstCdNmByLg').val(selectedData['univMstCdNmByLg']);
}

function handlerBankAccountRowSelect(selectedData){
	$('#bkCd').val(selectedData['bkCd']);
	$('#bkBoCd').val(selectedData['bkBoCd']);
	$('#accountNumber').val(selectedData['vesCoCd']);
	$('#bkNm').val(selectedData['bkNm']);
	$('#bkBoNm').val(selectedData['bkBoNm']);
	$('#holderNm').val(selectedData['holderNm']);
	$('#swiftCd').val(selectedData['swiftCd']);
}

function handlerCountryRowSelect(selectedData){
	$('#ctryCd').val(selectedData['ctryCd']).change();
	$('#ctryNm').val(selectedData['ctryNm']);
}

function handlerTAGNAMemberRowSelect(selectedData){
	$('#taaMemCd').val(selectedData['taaMemCd']);
	$('#taaMemNm').val(selectedData['taaMemNm']);
}

function handlerTAGNABaseRowSelect(selectedData){
	$('#taaMemCd').val(selectedData['taaMemCd']);
	$('#taaMemNm').val(selectedData['taaMemNm']);
	$('#taaMemAccountId').val(selectedData['taaMemAccountId']);
	$('#taaMemAccountNm').val(selectedData['taaMemAccountNm']);
}

function handlerContractorTAGNACheckbookRowSelect(selectedData){
	$('#trdrTgnaSttlNo').val(selectedData['trdrTgnaSttlNo']);
	$('#taaMemAcId').val(selectedData['taaMemAcId']);
	$('#taaMemAcNm').val(selectedData['taaMemAcNm']);
	$('#taaMemNm').val(selectedData['taaMemNm']);
	$('#trdrTgnaSttlIssuedDivCd').val(selectedData['trdrTgnaSttlIssuedDivCd']);
	$('#trdrTgnaSttlIssueDate').val(selectedData['trdrTgnaSttlIssueDate']);
	$('#trdrTgnaSttlClseDate').val(selectedData['trdrTgnaSttlClseDate']);
	$('#payPlanDate').val(selectedData['payPlanDate']);
	$('#bankChargeLmtDate').val(selectedData['bankChargeLmtDate']);
	$('#balancedPayAmt').val(selectedData['balancedPayAmt']);
	$('#projNo').val(selectedData['projNo']);
}

function handlerRouteRegistrationRowSelect(selectedData){
	$('#ofrNo').val(selectedData['ofrNo']);
	$('#mdNm').val(selectedData['mdNm']);
	$('#itmUniqNo').val(selectedData['itmUniqNo']);
	$('#tradingTermNm').val(selectedData['tradingTermNm']);
	$('#univMstCdNmS400').val(selectedData['univMstCdNmS400']);
	$('#univMstCdNmS190').val(selectedData['univMstCdNmS190']);
	$('#drvCanBeLoadedFlg').val(selectedData['drvCanBeLoadedFlg']);
	$('#portNm1').val(selectedData['portNm1']);
	$('#portNm2').val(selectedData['portNm2']);
	$('#memId').val(selectedData['memId']);
	$('#memNm').val(selectedData['memNm']);
	$('#memCoNm').val(selectedData['memCoNm']);
	$('#consigneeNm').val(selectedData['consigneeNm']);
	$('#consigneeCoNm').val(selectedData['consigneeCoNm']);
}

function handlerOtherInformationRowSelect(selectedData){
	$('#mkCd').val(selectedData['ofrNo']);
	$('#mkNm').val(selectedData['mdNm']);
	$('#mdCd').val(selectedData['itmUniqNo']);
	$('#mdNm').val(selectedData['tradingTermNm']);
	$('#bodyTypeCd').val(selectedData['univMstCdNmS400']);
	$('#bodyTypeNm').val(selectedData['univMstCdNmS190']);
}

function handlerCaseInformationRowSelect(selectedData){
	$('#exCd').val(selectedData['exCd']);
	$('exText').val(selectedData['exText']);
	$('#exPrdcList').val(selectedData['exPrdcList']);
}
function handlerPortRowSelect(selectedData){
	$('#ctryCd').val(selectedData['ctryCd']);
	$('#ctryNm').val(selectedData['ctryNm']);
	$('#portCd').val(selectedData['portCd']);
	$('#portNm').val(selectedData['portNm']);
	var rowCount = $('#tableForm >tbody tr').length;
	for (var i = 0; i < rowCount; i++) {
		$('#ladingPlanFormList'+ i + '\\.ctryCd').val(selectedData['ctryCd']);
		$('#ladingPlanFormList'+ i + '\\.ctryNm').val(selectedData['ctryNm']);
		$('#ladingPlanFormList'+ i + '\\.portCd').val(selectedData['portCd']);
		$('#ladingPlanFormList'+ i + '\\.portNm').val(selectedData['portNm']);
	}
	
}

function handlerMNG510401RowSelect(selectedData){
	$('#userId').val(selectedData['userId']);
	$('#dispUserNm').val(selectedData['dispUserNm']);
}

function handlerMNG512701RowSelect(selectedData){
	$('#bodyTypeNm').val(selectedData['bodyTypeNm']);
	$('#bodyTypeCd').val(selectedData['bodyTypeCd']);
	$('#gdCd').val(selectedData['gdCd']);
	$('#gdNm').val(selectedData['gdNm']);
	$('#mkCd').val(selectedData['mkCd']);
	$('#mkNm').val(selectedData['mkNm']);
	$('#mdCd').val(selectedData['mdCd']);
	$('#mdNm').val(selectedData['mdNm']);
}


// 画面を移動する
function redirectHandler(action, name, val) {
	var form = $('<form/>').addClass('hidden');
	form.attr('action', action);
	form.attr('method', 'POST');
	var inp = $('<input/>').val(val).attr('name', name);
	form.append(inp);
	$(document.body).append(form);
	$(form).submit();
}

/**
 * @author: duc.bv
 * @param input
 * @returns boolean
 */
function updateFormInput(input) {
	// update page index
	var parentForm = input.form;
	// update page
	var page = $(parentForm).find('#page')[0];
	$(page).val("1");
	// update sort by
	var sortBy = $(parentForm).find('#sortBy')[0];
	$(sortBy).val("0");
	// update sort type
	var sortType = $(parentForm).find('#sortType')[0];
	$(sortType).val("ASC");
	return true;
}
// kiem tra neu can thiet phai trim text
function isTrimText(elem) {
	// neu khong phai la input thi rereturn
	if(!$(elem).is('input')) {
		return false;
	}
	// kiem tra type co phai la submit hay khong
	var type = $(elem).attr('type');
	if(type !== 'submit') {
		return true;
	}
	return false;
	
}

// trim text
function trimText(input) {
	var val = $(input).val();
	$(input).val(val.trim());
}

/**
 * 中止ボタンを押下
 * @returns
 */
function resetRegister() {
    var form = $('#registerCSVFrm');
    var action = form.attr('action');
    var path = action.substring(0,action.lastIndexOf('/'));
    document.location.href = path;
}

/**
 * データ更新確認
 * @returns
 */
function confirmUpdateData() {
	var r = confirm("更新をおこいます。よろしいですか？");
	if (r == true) {
		return true;
	}
	return false;
}
/**
 * データ登録確認
 * @param form
 * @returns
 */
function confirmRegisterData(form) {
	var r = confirm("登録をおこいます。よろしいですか？");
	if (r == true) {
		return true;
	}
	return false;
	
	//insert-btn-common
}
/**
 * データ削除確認
 * @returns
 */
function confirmDeleteData() {
	var r = confirm("削除をおこいます。よろしいですか？");
	if (r == true) {
		return true;
	}
	return false;
}

/**
 * リセットボタン押下
 * @returns
 */
function resetUpdate() {
	// TODO message
	var r = confirm("データが保存されていません。データを再ロードしてもよろしいでしょうか。");
	if (r == true) {
		// location.reload();
		window.location.reload();
	}
}

/**
 * @author hung.pd
 */
function downloadCSV(action){
    var form = createPostFormSubmit(action);
    form.submit();
}
/**
 * @author hung.pd
 * 
 */

function openWindowWebFont(url) {
    var contextUrlWebFont = "http://localhost:8080/"; // TODO
    window.open(url,'_blank', 'location=yes,height=570,width=520,scrollbars=yes,status=yes');
}

 window.isArray = function(testObject) {
	return Object.prototype.toString.call(testObject) === '[object Array]';
};


function getScrollbarWidth() {
    var outer = document.createElement("div");
    outer.style.visibility = "hidden";
    outer.style.width = "100px";
    outer.style.msOverflowStyle = "scrollbar"; // needed for WinJS apps

    document.body.appendChild(outer);

    var widthNoScroll = outer.offsetWidth;
    // force scrollbars
    outer.style.overflow = "scroll";

    // add innerdiv
    var inner = document.createElement("div");
    inner.style.width = "100%";
    outer.appendChild(inner);        

    var widthWithScroll = inner.offsetWidth;

    // remove divs
    outer.parentNode.removeChild(outer);

    return widthNoScroll - widthWithScroll;
}

function drawHorizontalScrollBar(width) {
	var scrollBarWidth = getScrollbarWidth();
	return '<div style="width: '+width+'px; height: '+scrollBarWidth+'px; overflow:auto; background: red;"><div style="width: '+(width*2)+'px; height: '+scrollBarWidth+'px"></div></div>'
}

function drawVerticalScrollBar(height) {
	var scrollBarWidth = getScrollbarWidth();
	return '<div style="height: '+height+'px; width: '+scrollBarWidth+'px; overflow:auto; background: red;"><div style="height: '+(height*2)+'px; width: '+scrollBarWidth+'px"></div></div>'
}

//MinhLS do redirect to url
function doRedirect(url) {
    window.location= url;
    
}
