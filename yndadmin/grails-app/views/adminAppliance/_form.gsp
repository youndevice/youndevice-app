<div class="form-group">
    <label class="col-md-4 control-label" for="webStatus">Web Status</label>

    <div class="col-md-2">
        <g:select name="webStatus" value="${appliance?.webStatus}" class="form-control" from="${com.ynd.core.enums.WebStatus.values()}"/>
    </div>
</div>

<div class="form-group">
    <label class="col-md-4 control-label" for="actualDeviceStatus">Actual Device Status</label>

    <div class="col-md-2">
        <g:textField name="actualDeviceStatus" value="${appliance?.actualDeviceStatus}" class="form-control" />
    </div>
</div>

