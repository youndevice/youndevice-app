<div class="form-group">
    <label class="col-md-4 control-label" for="deviceType">Device Type</label>

    <div class="col-md-2">
        <g:select name="deviceType" value="${device?.deviceType}" class="form-control" from="${com.ynd.core.enums.DeviceType.values()}"/>
    </div>
</div>

<div class="form-group">
    <label class="col-md-4 control-label" for="applianceType">Appliance Type</label>

    <div class="col-md-2">
        <g:select name="applianceType" value="${device?.applianceType}" class="form-control" from="${com.ynd.core.enums.ApplianceType.values()}"/>
    </div>
</div>

