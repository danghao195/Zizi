

function doRedirect(mkCd, action) {
	 window.location.href = action + "?mdCd=" + mkCd;
}

function doSearch() {
    $("#sortType").val("ASC");
    $("#sortBy").val(0);
    $("#page").val(1);
}
function doDetails(mkCd, action) {
    window.location.href = action + "?mkCd=" + mkCd;
}