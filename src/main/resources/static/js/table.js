function firstPagebtn(a) {
    var searchForm = getFormTarget(a);
	if(isUnset(searchForm)) {
		document.getElementById('page').value = 1;
	    $("#searchForm").submit();
	} else {
		var form = searchForm;
		form.page.value = parseInt(1);
	    createHiddenInput(form, "paging", true);
	    searchPopup(form);
	}
}

function lastPagebtn(n, a) {
	var searchForm = getFormTarget(a);
	if(isUnset(searchForm)) {
		document.getElementById('page').value = n;
	    $("#searchForm").submit();
	} else {
		var form = searchForm;
		form.page.value = parseInt(n);
	    createHiddenInput(form, "paging", true);
	    searchPopup(form);
	}
    
}

function SubmenuNextData() {
    var nextId = document.getElementById("next").value;
    next(nextId);
    return false;
}

function SubmenuPrevData() {
    var prevId = document.getElementById("prev").value;
    prev(prevId);
    return false;
}

function searchSubmit(a) {
    var n = parseInt(a.id);
    document.getElementById('page').value = n;
    $("#searchForm").submit();
}

function pagination(a) {
	var searchForm = getFormTarget(a);
	if(isUnset(searchForm)) {
		var form = document.getElementById('searchForm');
	    form.page.value = parseInt(a.id);
	    createHiddenInput(form, "paging", true);
	    $("#searchForm").submit();
	} else {
		var form = searchForm;
		form.page.value = parseInt(a.id);
	    createHiddenInput(form, "paging", true);
	    searchPopup(form);
	}
}

function getFormTarget(a) {
	if($(a).find('.search-form').length >= 1) {
		return $(a).find('.search-form')[0]
	} else {
		if(isUnset(a) || isUnset(a.parentNode)) {
			return null;
		} else {
			return getFormTarget(a.parentNode);
		}
		
	}
}

function doSort(sortIndex, elem) {
	var form = getFormTarget(elem);
	if(isUnset(form)) {
		if ($("#sortBy").val() == sortIndex && $("#sortType").val() == 'ASC') {
	        $("#sortType").val('DESC');
	    } else {
	        $("#sortBy").val(sortIndex);
	        $("#sortType").val('ASC');
	    }
	    var form = document.getElementById('searchForm');
	    createHiddenInput(form, "paging", true);
	    $("#searchForm").submit();
	} else {
		// for popup dialog
		if ($($(form).find("[name=sortIndex]")[0]).val() == sortIndex && $($(form).find("[name=sortType]")[0]).val() == 'ASC') {
			$($(form).find("[name=sortType]")[0]).val('DESC');
	    } else {
	    	$($(form).find("[name=sortIndex]")[0]).val(sortIndex);
	    	$($(form).find("[name=sortType]")[0]).val('ASC');
	    }
	    createHiddenInput(form, "paging", true);
	    searchPopup(form);
	}
    
}


function nextbtn(n, a) {
	var searchForm = getFormTarget(a);
	if(isUnset(searchForm)) {
		document.getElementById('page').value = ++n;
	    $("#searchForm").submit();
	} else {
		var form = searchForm;
		form.page.value = parseInt(++n);
	    createHiddenInput(form, "paging", true);
	    searchPopup(form);
	}
	
}

function prevbtn(n, a) {
	var searchForm = getFormTarget(a);
	if(isUnset(searchForm)) {
		document.getElementById('page').value = --n;
	    $("#searchForm").submit();
	} else {
		var form = searchForm;
		form.page.value = parseInt(--n);
	    createHiddenInput(form, "paging", true);
	    searchPopup(form);
	}
}
function getPropId(id){
	doRedirect(id, 'detail');
}
function next(id) {
    if (id == -1) {
        document.getElementById("nextDetail").disabled = true;
        document.getElementById("nextDetail").style.color = "gray";
    } else {
        doRedirect(id, 'detail');
    }
}

function prev(id) {
    if (id == -1) {
        document.getElementById("prevDetail").disabled = true;
        document.getElementById("prevDetail").style.color = "gray";
    } else {
        doRedirect(id, 'detail');
    }
}

function nextDetail(id, action) {
    if (id == -1) {
        document.getElementById("nextDetail").disabled = true;
        document.getElementById("nextDetail").style.color = "gray";
    } else {
        doRedirect(id, action);
    }
}

function prevDetail(id, action) {
    if (id == -1) {
        document.getElementById("prevDetail").disabled = true;
        document.getElementById("prevDetail").style.color = "gray";
    } else {
        doRedirect(id, action);
    }
}

function SeiPagerNextPage() {
    var curPage = parseInt(document.getElementById('curPage').value);
    var cntPage = parseInt(document.getElementById('cntPage').value);
    if (curPage != cntPage)
        nextbtn(curPage);
    return false;
}
function SeiPagerLastPage() {

    var cntPage = parseInt(document.getElementById('cntPage').value);
    lastPagebtn(cntPage);
    return false;
}
function SeiPagerPrevPage() {
    var curPage = parseInt(document.getElementById('curPage').value);
    if (curPage != 1) {
        prevbtn(parseInt(curPage));
    }
    return false;
}

function SeiPagerFirstPage() {
    firstPagebtn();
    return false;
}
