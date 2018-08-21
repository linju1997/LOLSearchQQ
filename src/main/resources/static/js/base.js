var Tip_Success = new $.zui.Messager({icon: 'bell', type: 'success'});
var Tip_Info = new $.zui.Messager({icon: 'bell', type: 'info'});

var Tip_Danger = new $.zui.Messager({icon: 'bell', type: 'danger'});
function setCookie(name, value) {
    let exp = new Date();
    exp.setTime(exp.getTime() + 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else return null;
}