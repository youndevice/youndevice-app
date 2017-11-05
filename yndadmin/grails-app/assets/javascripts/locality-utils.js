(function ($, w) {
    function cachedAjaxRequest(url, payload, callback) {
        var key = url + JSON.stringify(payload || {});
        if (!w.__cache) {
            w.__cache = {};
        }
        var deferred = w.__cache[key];
        if (!deferred) {
            deferred = $.get(url, payload);
            w.__cache[key] = deferred;
        }
        deferred.done(callback);
        deferred.fail(function () {
            w.__cache[key] = null;
        })
    }

    function noSelectionOptionElement($select) {
        var noSelectionText = $.trim($select.data("noSelection"));
        return noSelectionText.length > 0 ? "<option value=''>" + ( $select.data("noSelection") || "-Select-") + "</option>" : '';
    }

    function clearAndDisable($select) {
        $select.off('change', selectBoxChangeHandler);
        $select.html(noSelectionOptionElement($select));
        $('input#' + $select.prop('id') + "Name").val('');
        $select.val('');
        $select.data('selectedValue', '');
        $select.prop('disabled', true);
        var $dependent = $('select#' + $select.data("dependent"));
        if ($dependent.size()) {
            clearAndDisable($dependent);
        }
    }

    function selectBoxChangeHandler() {
        var $select = $(this);
        var currentSelectedValue = $.trim($select.val());
        var correspondingNameInput = $('input#' + $select.prop('id') + "Name");
        if (correspondingNameInput.size() > 0) {
            var selectedItemText = currentSelectedValue.length > 0 ? $select.find("[value='" + currentSelectedValue + "']").text() : undefined;
            correspondingNameInput.val(selectedItemText);
        }
        $select.data('selectedValue', currentSelectedValue);
        var $dependent = $('select#' + $select.data("dependent"));
        if ($dependent.size() > 0) {
            clearAndDisable($dependent);
            setupLocalityWidget($dependent)
        }
    }

    function fetchDataAndInitialise($select, payload) {
        $select.children().remove().end();
        cachedAjaxRequest($select.data("remoteUrl"), payload, function (responseData) {
            var isMultiple = typeof $select.attr("multiple") !== typeof undefined && $select.attr("multiple") !== false;
            var selectedValue = $.trim($select.data("selectedValue"));
            var convertedSelectedValue = [];
            if(isMultiple) {
                selectedValue = selectedValue.split("$$");
                convertedSelectedValue = $.map(selectedValue, function(val, i) {
                    if($.isNumeric(val))
                        return parseInt(val);
                    })

            }
            $('input#' + $select.prop('id') + "Name").val($select.find("[value='" + selectedValue + "']").text());
            var optionKey = $select.data('optionKey') || "id";
            var optionValue = $select.data('optionValue') || "name";
            var options = noSelectionOptionElement($select) + $.map(responseData, function (option) {
                    if (option[optionValue]) {
                        var selectedAttr;
                        if(isMultiple) {
                            selectedAttr = ($.inArray(option[optionKey], selectedValue) > -1 || $.inArray(option[optionValue], selectedValue) > -1
                            || $.inArray(option[optionKey], convertedSelectedValue) > -1 || $.inArray(option[optionValue], convertedSelectedValue) > -1) ? "selected" : "";
                        } else {
                            selectedAttr = (option[optionKey] == selectedValue || option[optionValue] == selectedValue) ? "selected" : "";
                        }
                        return "<option " + selectedAttr + " value='" + option[optionKey] + "'>" + option[optionValue] + "</option>";
                    } else {
                        return ""
                    }
                }).join("\n");
            $select.html(options);
            $select.off('change', selectBoxChangeHandler).on('change', selectBoxChangeHandler);
            $select.prop('disabled', false);
            var afterSelect = $select.data('after') || false;
            if(afterSelect) {
                $select.trigger("triggerValueChange");
            }
        });
    }

    function setupLocalityWidget($select) {
        var $dependsOn = $('select#' + $select.data("dependsOn"));
        var payload = {};
        if ($dependsOn.size() > 0) {
            var dependsOnValue = $.trim($dependsOn.data('selectedValue'));
            if (dependsOnValue.length > 0) {
                payload[$dependsOn.data("filterParam")] = dependsOnValue;
                fetchDataAndInitialise($select, payload)
            } else {
                clearAndDisable($select);
            }
        } else {
            fetchDataAndInitialise($select, payload)
        }
    }

    w.initialiseLocalityWidgets = function () {
        $('.locality-widget:not(.ignored)').each(function () {
            var $select = $(this);
            if (!$select.data('processed')) {
                $select.data('processed');
                setupLocalityWidget($select);
            }
        });
    };
    $(function () {
        w.initialiseLocalityWidgets();
    })
}(jQuery, window));