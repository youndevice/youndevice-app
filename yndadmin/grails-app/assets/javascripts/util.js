"use strict";
var AppConstants = {
    DATE_FORMAT_LONG: "DD MMM YYYY HH:mm",
    DATA_FORMAT_STRING: "DD MMM YYYY"
};

var DataTableUtils = {
    safehtml: function (data, type, row) {
        // console.log("In SAFE HTML");
        // console.log("Data: ", data)
        // console.log("Data: ", type)
        // console.log("Data: ", row)
        if (data && type == 'display') {
            // console.log("Data: ", data);
            if(typeof data === 'string'){
                return DataTableUtils.sanitizeHTML(data);
            }else{
                return data;
            }

        }
    },
    sanitizeHTML: function(text){
        return text.replace( /&/g, '&amp;' )
            .replace( /</g, '&lt;' )
            .replace( />/g, '&gt;' );
    },
    renderFormattedDate: function (data, type, row, meta) {
        if (data) {
            var dateInString = data;
            var formattedDate = parseAndFormatMoment(dateInString, AppConstants.DATE_FORMAT_LONG);
            return formattedDate;
        }
    },

    renderOnlyDate: function (data, type, row, meta) {
        if (data) {
            var dateInString = data;
            var formattedDate = parseAndFormatMoment(dateInString, AppConstants.DATA_FORMAT_STRING);
            return formattedDate;
        }
    }
};
window.isDefined = function (target) {
    return (target != null && target != 'null' && typeof(target) != 'undefined')
};

function log() {
    if (console) {
        console.log.apply(console, arguments);
    }
}
String.prototype.toBoolean = function () {
    return Boolean(this) && (this === 'true');
};

function bindDateSelector() {
    jQuery('.dateSelector').each(function () {
        jQuery(this).datepicker({dateFormat: "dd/mm/yy"});
    })
}

Array.prototype.compare = function (testArr) {
    if (this.length != testArr.length) return false;
    for (var i = 0; i < testArr.length; i++) {
        if (this[i].compare) {
            if (!this[i].compare(testArr[i])) return false;
        }
        if (this[i] !== testArr[i]) return false;
    }
    return true;
};

Array.prototype.remove = function (element) {
    for (var i = this.length - 1; i >= 0; i--) {
        if (this[i] === element) {
            this.splice(i, 1);
        }
    }
};

var commonErrorCallBack = function (data) {
    showErrorMsg(data ? data.message : "Some Error occurred please try again later");
};

function showSuccessMsg(msg, timeout) {
    msg && toastr.success(msg, "", {timeOut: timeout || 3500});
}

function showErrorMsg(msg, timeout) {
    msg && toastr.error(msg, "", {timeOut: timeout || 6000});
}

function removeExistingMessageHolder() {
    // $('.messageContainer').empty();
}

function goBack() {
    history.back();
    setTimeout(function () {
        location.reload();
    }, 1500);
}

function showStatus(data) {
    data.status ? showSuccessMsg(data.message) : showErrorMsg(data.message);
}

function getLinkUrl(elementSelector) {
    return elementSelector.attr('href');
}

function getFormUrl(elementSelector) {
    return $(elementSelector).attr('action');
}

function preventEvent(event) {
    event.preventDefault();
}

function search() {
    $('#search-input').bind('keyup', function () {
        var searchText = $(this).val();
        if (searchText != "") {
            $(".searchable-table tbody>tr").hide();
            $('.searchable-table td:contains("' + searchText + '")').parent().show();
        } else {
            $(".searchable-table tbody>tr").show();
        }
    })
}

function bindDateTimePicker() {
    var datePickerTag = $(".datepicker");
    datePickerTag.datepick({
        changeMonth: true,
        changeYear: true,
        yearRange: "-100:+50",
        dateFormat: "dd/mm/yyyy",
        constrainInput: "true",
        onSelect: function (dateText) {
            $(this).trigger("change");//trigger change event of container textbox
            $(this).valid()
        }
    });
    datePickerTag.keypress(function (event) {
        event.preventDefault();
    });
}

function bindDatePicker(minDate) {
    var datePickerTag = $(".datepicker");
    var options = {
        changeMonth: true,
        changeYear: true,
        yearRange: "-100:+50",
        dateFormat: "dd/mm/yyyy",
        constrainInput: "true",
        onSelect: function (dateText) {
            $(this).trigger("change");
            $(this).valid()
        }
    };
    if (minDate >= 0) {
        options.minDate = minDate;
    }
    datePickerTag.datepick(options);
    datePickerTag.keypress(function (event) {
        event.preventDefault();
    });
}

function bindTimePicker() {
    var timePickerTag = $(".timepicker");
    timePickerTag.timepicker({
        timeFormat: 'HH:mm:ss',
        minTime: '00:00:00',
        maxHour: 23,
        maxMinutes: 60,
        startTime: new Date(0, 0, 0, 0, 0, 0),
        dropdown: true,
        scrollbar: true,
        dynamic: true,
        showInputs: false,
        interval: 20,
        change: function () {
            $(this).valid();
        }
    });
    timePickerTag.keypress(function (event) {
        event.preventDefault();
    });
}

function showBootBoxDialog(title, successCallback, failureCallback) {
    var dialog = bootbox.dialog({
        message: title,
        buttons: {
            yes: {
                label: "Yes",
                className: "btn-danger",
                callback: function () {
                    dialog.modal('hide');
                    successCallback && successCallback()
                }
            },
            no: {
                label: "No",
                className: "btn-primary",
                callback: failureCallback || $.noop
            }
        }
    });
}

function showBootBoxAlert(title, callback) {
    bootbox.alert(title, callback || $.noop());
}

function convertINToDate(date) {
    var splitDate = date.split("/");
    var d = new Date(splitDate[2] + "-" + splitDate[1] + "-" + splitDate[0]);
    return d.getTime();
}

function checkDate(fromDate, toDate) {
    return !((fromDate && toDate) && (convertINToDate(fromDate) > convertINToDate(toDate)));
}

function compareStringWithBoolean(value) {
    return (value == 'true')
}

function clearFilterData() {
    $(".filtersForm :input").val("");
}

function validateFromAndToDate(fromDateId, toDateId) {
    var datePickerTagFrom = $("#" + fromDateId + "");
    var datePickerTagTo = $("#" + toDateId + "");
    datePickerTagTo.datepick({
        changeMonth: true,
        changeYear: true,
        yearRange: "-100:+50",
        dateFormat: "dd/mm/yyyy",
        constrainInput: "true",
        onSelect: function (dateText, inst) {
            if (datePickerTagFrom.val()) {
                if (checkDate(datePickerTagTo.val(), datePickerTagFrom.val())) {
                    showErrorMsg("From Date should be less than to Date.");
                    setTimeout(function () {
                        removeExistingMessageHolder();
                    }, 5000)
                }
            }
        }
    });
    datePickerTagFrom.datepick({
        changeMonth: true,
        changeYear: true,
        yearRange: "-100:+50",
        dateFormat: "dd/mm/yyyy",
        constrainInput: "true",
        onSelect: function (dateText, inst) {
            if (datePickerTagTo.val()) {
                if (checkDate(datePickerTagTo.val(), datePickerTagFrom.val())) {
                    showErrorMsg("From Date should be less than to Date.");
                    setTimeout(function () {
                        removeExistingMessageHolder();
                    }, 5000)
                }
            }
        }
    });
}

function formatDate(d) {
    return parseAndFormatMoment(d, AppConstants.DATE_FORMAT_LONG)
}

function showAndRemoveResponseMessage() {
    /*
     $(window).scrollTop(0);
     setTimeout(function () {
     removeExistingMessageHolder();
     }, 3000);
     */
}

function trimText() {
    $("input[type=text],textarea").each(function (index, object) {
        var text = $(object).val();
        $(object).val(text.trim())
    });
}

function limitText(limitField, limitCount, limitNum) {
    if (limitField.value.length > limitNum) {
        limitField.value = limitField.value.substring(0, limitNum);
    } else {
        limitCount.value = limitNum - limitField.value.length;
    }
}

function moveAll(from, to, destination, hiddenElementName) {
    $('#' + from + ' option').remove().appendTo('#' + to);
    if (destination && hiddenElementName) {
        refreshContentSelected(destination, hiddenElementName);
    }
    $('#' + to).parent().find('label.error').remove();
    $('#' + to).removeClass('error');
}

function moveSelected(from, to, destination, hiddenElementName) {
    $('#' + from + ' option:selected').remove().appendTo('#' + to);
    if (destination && hiddenElementName) {
        refreshContentSelected(destination, hiddenElementName);
    }
    $('#' + to).parent().find('label.error').remove();
    $('#' + to).removeClass('error');
}

function refreshContentSelected(toElement, hiddenElementName) {
    var to = $('#' + toElement);
    var span = to.parent().find('.hidden_selected_items');
    span.empty();
    to.find('option').each(function () {
            span.append($("<input>", {type: 'hidden', name: hiddenElementName, value: jQuery(this).val()}));
        }
    )
}
function isEnglishLanguage(value) {
    //var exp = /^[\x20-\x7E\n]+$/;
    var exp = /[\n\u0000-\u007F\u02B0-\u02CF\u02B0-\u032F\u0300-\u036F\u1D00-\u20FF\u1D80-\u217F\u1DC0-\u21BF\u1E00-\u21FF\u2000-\u23FF\u2070-\u246F\u20A0-\u211F\u2100-\u217F\u2200-\u227F]+$/igm;
    if (value && value.length > 0) {
        return $.trim(value).length > 0 ? new RegExp(exp).test(value) : false
    }
    return true
}
function isArabicLanguage(value) {
    if (value && value.length > 0) {
        var exp = "[\u0600-\u06ff]|[\u0750-\u077f]|[\ufb50-\ufc3f]|[\ufe70-\ufefc]|[0-9]";
        return new RegExp(exp).test(value)
    }
    return true
}

function showFlashMessagesOnPageLoad() {
    $(['info', 'success', 'error']).each(function (index, value) {
        var $element = $('.message-text-' + value + ':first', '.messageContainer');
        if ($element.size() > 0 && $.trim($element.text()).length > 0) {
            toastr[value]($.trim($element.text()));
            $element.empty();
        }
    })
}

$(document).ready(function () {
    var timer;

    $(window).unload(function () {
        $(".image").show()
    });

    $(document).unload(function () {
        $(".image").show()
    });

    $(document).on("beforeunload", function () {
        $(".image").show()
    });

    window.onunload = function () {
        var filterForm = $("form.filterForm");
        if (filterForm.size() > 0) {
            filterForm.reset();
        }
    };
    var logoutUrl = $("#logoutUrl").val();

    $('[data-toggle="tooltip"]').tooltip();

    $(document).ajaxStart(function () {
        timer = setTimeout(function () {
            $(".image").show();
        }, 500);
    });

    $(document).ajaxStop(function () {
        clearTimeout(timer);
        $(".image").hide();
    });

    $(".datePicker").on("copy cut paste drop", function () {
        return false;
    });

    showFlashMessagesOnPageLoad();
});
$(document).on("click", ".deleteUser", function (e) {
    var element = this;
    e.preventDefault();
    showBootBoxDialog("Are you sure you want to delete this user?", function () {
        location.href = $(element).attr('href')
    }, function () {
    })
});

$(document).on("click", ".deleteAmenity", function (e) {
    var element = this;
    e.preventDefault();
    showBootBoxDialog("Are you sure you want to delete this amenity?", function () {
        location.href = $(element).attr('href')
    }, function () {
    })
});

$(document).on("click", ".deleteCategory", function (e) {
    var element = this;
    e.preventDefault();
    showBootBoxDialog("Some part of Application might have dependency in this Category <br><br><br> Are you sure you want to delete this Category?", function () {
        location.href = $(element).attr('href')
    }, function () {
    })
});

$(document).on("paste", ".disablePaste", function (e) {
    e.preventDefault(); //disable paste.
});

$(document).on('mouseenter', '.ellipsis', function () {
    var $this = $(this);
    if (this.offsetWidth < this.scrollWidth && !$this.attr('title')) {
        $this.attr('title', $this.text());
    }
});

window.CLOUDINARY = (function () {
    //Documentation available at https://github.com/cloudinary/pkg-cloudinary-jquery
    var cl = cloudinary.Cloudinary.new();
    cl.init();
    return cl;
}());

function validateInvalidInputs(input) {
    var $form = $(input).closest('form');
    $('input.error,select.error,textarea.error', $form).each(function () {
        $(this).valid();
    })
}

function setupCounterWidget() {
    $('.max-allowed-widget').each(function () {
        var $element = $(this);
        $(this).insertDivCounter();
    });
}

function updateFileUploadPreview(input) {
    var $previewElement = $('#' + $(input).data("previewElementId"));
    if ($previewElement.size() && input.files && input.files[0] && input.files[0].size <= (10 * 1000 * 1000)) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $previewElement.prop('src', e.target.result);
        };
        $previewElement.removeAttr('poster');
        reader.readAsDataURL(input.files[0]);
    }
    validateInvalidInputs(input);
}

function enableDisableFileUpload(checkbox) {
    var isChecked = $(checkbox).is(':checked');
    $(checkbox).closest('tr')
        .find('[type=file]')
        .prop('disabled', isChecked)
        .parent('div')
        .css('visibility', isChecked ? 'hidden' : 'visible');
    validateInvalidInputs(checkbox);
}

function checkMinMaxRange(min, max, val) {
    return (val >= min && val <= max)
}

function selectAllCheckBox(parentId, childClass) {
    $(parentId).change(function () {
        $(childClass).prop('checked', $(this).prop("checked"));
    });
}

function removeProfileImage(element) {
    var $parentForm = $(element).closest('form');
    var hiddenFieldName = $(element).data('removeImageHiddenFieldName') || 'removeProfilePicture';
    showBootBoxDialog($(element).data("removeMessage"), function () {
        if ($parentForm.valid()) {
            $('.image-main-container').remove();
            var $hiddenRemoveInput = $('input[name=' + hiddenFieldName + ']', $parentForm);
            if ($hiddenRemoveInput.size() > 0) {
                $hiddenRemoveInput.remove();
            }
            $parentForm.append("<input type='hidden' name='" + hiddenFieldName + "' value='true'/>");
            $parentForm.submit();
        }
    });
    return false;
}

jQuery.validator.addMethod("valueRequired", function (value, element) {
    return $.trim(value) !== '';
}, "Value is missing.");

function parseAndFormatMoment(momentInString, outputFormat) {
    return moment(momentInString).format(outputFormat);
}

function initialiseAutoSuggests() {
    var $autoSuggest = jQuery(".auto-suggest");
    var url = $autoSuggest.attr("fetch-url");
    var hiddenFieldId = $autoSuggest.attr("hidden-field-id");
    var $hiddenField = $('#' + hiddenFieldId);
    var source = function (request, response) {
        var searchTerm = $.trim(request.term);
        if (/^\d+$/.test(searchTerm) || searchTerm.length > 2) {
            $.get(url, {
                term: searchTerm,
            }).done(function (data) {
                autoCompleteAjaxResponseHandler(response, data)
            });
        } else {
            autoCompleteAjaxResponseHandler(response, {});
        }
    };
    var autoCompleteAjaxResponseHandler = function (response, data) {
        var result = [];
        if (!data.length) {
            result = [
                {
                    label: "No data found.",
                    value: " "
                }
            ];
        } else {
            result = $.map(data, function (item) {
                return {
                    entityId: item.value,
                    label: item.label,
                    value: item.label,
                    displayValue: item.label
                }
            });
        }
        response(result);
    };
    var onSelect = function (event, ui) {
        if (ui.item.label != 'No data found.') {
            $hiddenField.val(ui.item.entityId);
            var value = ui.item.label;
            $(this).val(value);
        }
    };
    var onClose = function (event, ui) {
    };
    $autoSuggest.autocomplete({source: source, select: onSelect, close: onClose});
}
$(document).ready(function () {
    initialiseAutoSuggests();
});

// var DtDataTable = function (elem, opts) {
//     var sanitizeColumns= function(columns){
//         var sanitizedColumns = [];
//         $.each(columns, function(index, column){
//             var renderCallback = column.render;
//             if(renderCallback){
//                 console.log("Column: ", column.render);
//                 console.log("exists: ", true)
//             }else{
//                 column["render"] = null;//DataTableUtils.safehtml;
//             }
//             sanitizedColumns.push(column);
//         });
//         return sanitizedColumns;
//     };
//     if(opts){
//         var columns = opts["columns"];
//         opts = $.extend(opts, {"columns": sanitizeColumns(columns)});
//     }
//     return elem.DataTable(opts);
// };

// var sanitizeColumns= function(columns){
//     var sanitizedColumns = [];
//     $.each(columns, function(index, column){
//         var renderCallback = column.render;
//         if(renderCallback){
//             // console.log("Column: ", column.render);
//             // console.log("exists: ", true)
//         }else{
//             console.log("applying custom render");
//             column["render"] = DataTableUtils.safehtml;
//         }
//         sanitizedColumns.push(column);
//     });
//     return sanitizedColumns;
// };