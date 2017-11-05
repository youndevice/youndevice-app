$.extend($.fn, {
    dirtyCheck: function (options) {
        if (!this.length) {
            if (options && options.debug && window.console) {
                console.warn("Nothing selected, can't validate, returning nothing.");
            }
            return;
        }

        var dirtyChecker = $.data(this[0], "dirtyChecker");
        if (!dirtyChecker) {
            dirtyChecker = new $.dirtyChecker(options, this[0]);
            $.data(this[0], "dirtyChecker", dirtyChecker);
        }
        return dirtyChecker;
    },
    isDirty: function () {
        if (!this.length) {
            return false;
        }
        var dirtyChecker = $.data(this[0], "dirtyChecker");
        if ($.type(dirtyChecker) == "undefined") {
            return false;
        }
        return dirtyChecker.dirtyFields.length > 0;
    },
    initDirtyCheck: function (options) {
        if (!this.length) {
            return false;
        }
        var dirtyChecker = $(this[0]).dirtyCheck(options);
        dirtyChecker.init();
        return this;
    },
    resetDirtyCheck: function (options) {
        if (!this.length) {
            return false;
        }
        var dirtyChecker = $(this[0]).dirtyCheck(options);
        dirtyChecker.adjust();
        return this;
    }
});

$.dirtyChecker = function (options, form) {
    this.settings = $.extend(true, {}, $.dirtyChecker.options, options);
    this.currentForm = form;
    this.dirtyFields = [];
};
$.extend($.dirtyChecker, {
    options: {
        ignoreDirtyCheckClass: "ignore-dirty-check",
        debug: false,
        warnOnNavigate: true
    },
    prototype: {
        multiSelectBoxValues: function ($select) {
            return $select.children().map(function () {
                return $(this).val();
            }).get().sort().join(",");
        },
        triggerDirtyStateChange: function () {
            this.settings.change.apply(this.currentForm, [this.dirtyFields.length == 0, this.dirtyFields])
        },
        setupDirtyCheck: function (updateNewElementOnly) {
            var dc = this;
            var $form = $(dc.currentForm);

            $('input[type=text], input[type=checkbox], input[type=radio], input[type=file], textarea', $form).each(function () {
                var $input = $(this);
                if ($input.hasClass(dc.settings.ignoreDirtyCheckClass)) {
                    return;
                }
                if (!$input.data('valueCalculator')) {
                    $input.data('valueCalculator', function () {
                        return ($input.is(':checkbox') || $input.is(':radio') ) ? $input.is(':checked') : $input.val();
                    });
                    $input.on('input', function () {
                        dc.formChangeHandler($input)
                    });
                }
                var existingValue = $input.data('oldValue');
                if (updateNewElementOnly) {
                    if ($.type(existingValue) == "undefined") {
                        $input.data('oldValue', $input.data('valueCalculator')());
                    }
                } else {
                    $input.data('oldValue', $input.data('valueCalculator')());
                }
            });

            $('select', $form).each(function () {
                var $select = $(this);
                if ($select.hasClass(dc.settings.ignoreDirtyCheckClass)) {
                    return;
                }
                if (!$select.data('valueCalculator')) {
                    $select.data('valueCalculator', function () {
                        if ($select.hasClass('locality-widget')) {
                            return $select.data('selectedValue');
                        }
                        if ($select.hasClass('multi-select')) {
                            return dc.multiSelectBoxValues($select);
                        }
                        return $select.val();
                    });

                    if ($select.hasClass('multi-select')) {
                        $select.on("DOMSubtreeModified", function () {
                            dc.formChangeHandler($select)
                        });
                    }
                }
                $select.data('oldValue', $select.data('valueCalculator')());
            });
        },
        formChangeHandler: function ($target) {
            var dc = this;
            if ($target.hasClass(dc.settings.ignoreDirtyCheckClass) || !$target.data('valueCalculator')) {
                return true;
            }
            var dirtyFields = dc.dirtyFields;
            var oldDirtyCount = dirtyFields.length;
            var propName = $target.prop('name');
            var value = $target.data('valueCalculator')();
            var formIsDirty = (value != $target.data('oldValue'));
            var index = dirtyFields.indexOf(propName);
            if (formIsDirty) {
                (index < 0) && dirtyFields.push(propName)
            } else {
                (index > -1) && dirtyFields.splice(index, 1)
            }
            if (dc.settings.debug) {
                console.log({field: $target.prop('name'), "Old: ": $target.data('oldValue'), "New: ": value});
                console.log({"Dirty fields": dirtyFields});
            }
            if ((oldDirtyCount == 0 && dirtyFields.length > 0) || (oldDirtyCount > 0 && dirtyFields.length == 0)) {
                dc.triggerDirtyStateChange();
            }
        },
        init: function () {
            var self = this;
            if ($.type(self.settings.change) == "undefined") {
                self.settings.change = function (notChanged) {
                    $('input[type=submit]', $(self.currentForm)).prop('disabled', notChanged);
                };
                $(self.currentForm).change(function (e) {
                    self.formChangeHandler($(e.target))
                });
            }

            $(window).off('beforeunload').on('beforeunload', function (e) {
                if (self.dirtyFields.length > 0 && self.settings.warnOnNavigate) {
                    var message = "You have unsaved changes on this page. Do you want to leave this page and discard your changes?";
                    e.returnValue = message;
                    return message;
                }
            });
            self.dirtyFields = [];
            self.setupDirtyCheck();
            self.triggerDirtyStateChange();
            if (self.settings.debug) {
                console.log("Dirty check applied successfully.")
            }
        },
        adjust: function () {
            this.setupDirtyCheck(true);
            this.triggerDirtyStateChange();
            if (this.settings.debug) {
                console.log("Dirty check adjusted successfully to consider new fields.")
            }
        }
    }
});