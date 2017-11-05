(function ($, w) {
    $(function () {
        var $placeholder = $('#commentPopupHtml');
        var clonedElement = $placeholder.clone();
        $placeholder.remove();
        w.promptForComment = function (message, callback, cancelCallback) {
            var tempHtml = clonedElement.clone();
            if (arguments.length > 1) {
                $('#commentWarningMessage', tempHtml).text(message).show();
            } else {
                callback = message;
            }
            bootbox.dialog({
                message: tempHtml.html(),
                buttons: {
                    "Submit": {
                        className: "btn-success promptSave tab-index",
                        callback: function () {
                            var commentText = $.trim(jQuery(".modal.in").find("textarea:eq(0)").val());
                            if (!commentText) {
                                return false;
                            } else {
                                try {
                                    callback && callback(commentText);
                                } catch (e) {
                                    return true;
                                }
                            }
                        }
                    },
                    "Cancel": {
                        className: "btn-default",
                        callback: function () {
                            cancelCallback && cancelCallback();
                        }
                    }
                }
            });
            setTimeout(function () {
                jQuery("#comment").focus();
                jQuery(".modal.in").find(".promptSave:eq(0)").prop('disabled', true);
                jQuery(".modal.in").find(".promptSave:eq(0)").attr('tabindex', 2);
            }, 500)
        }
    });
}(jQuery, window));