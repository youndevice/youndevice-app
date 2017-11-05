jQuery.validator.addMethod("valueRequired", function (value, element) {
    return $.trim(value) !== '';
}, "Value is missing.");

$.validator.addClassRules("imageFile", {
    "accept": "image/*",
    "image-size": true
});

$.validator.addClassRules("imageFileRequired", {
    "accept": "image/*",
    "required": true,
    "image-size": true
});

$.validator.addClassRules("videoFile", {
    "accept": "video/mp4,video/ogg,video/ogv,video/webm",
    "video-size": true
});

$.validator.addClassRules("videoFileRequired", {
    "accept": "video/mp4,video/ogg,video/ogv,video/webm",
    "required": true,
    "video-size": true
});

$.validator.addMethod("image-size", function (value, element, param) {
    return this.optional(element) || (element.files[0].size <= 10 * 1000 * 1000)
});

$.validator.addMethod("video-size", function (value, element, param) {
    return this.optional(element) || (element.files[0].size <= 40 * 1000 * 1000)
});
