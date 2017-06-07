
//定义标签库的命名空间
var UITag = new Object();
UITag.getValue = function(fieldId) {
	var fieldType = $("#field_" + fieldId).attr("fieldType");
	switch (fieldType) {
	case "text":
		return getDefaultFieldValue(fieldId);
		break;
	case "combobox":
		return getComboboxFieldValue(fieldId);
		break;
	case "numberbox":
		return getNumberboxFieldValue(fieldId);
		break;
	case "datebox":
		return getDateboxFieldValue(fieldId);
		break;
	case "textarea":
		return getDefaultFieldValue(fieldId);
		break;
	default:
	}
}

/**
 * 获得普通文本域的值
 */
UITag.getDefaultFieldValue = function(fieldId) {
	return $("#field_" + fieldId).val();
}

/**
 * 获得combobox表单域的值
 */
UITag.getComboboxFieldValue = function() {
	return $("#field_" + fieldId).combobox('getValue');
}

/**
 * 获得numberbox表单域的值
 */
UITag.getNumberboxFieldValue = function(fieldId) {
	return $("#field_" + fieldId).numberbox('getValue');
}

/**
 * 获得datebox表单域的值
 */
UITag.getDateboxFieldValue = function() {
	return $("#field_" + fieldId).datebox('getValue');
}

/**
 * 重置表单
 */
UITag.resetForm = function(formId) {
	$("#form_" + formId).form('clear');
}

/**
 * 返回form所有表单域的值序列,如果是中文，将采用URL encode
 */
UITag.getFormParamSerialize = function(formId) {
	return $("#form_" + formId).serialize().replace(/\+/g, " ");
}

/**
 * 将jquery.serialize()序列化字符串转成json对象
 */
UITag.serializeToJson = function(string, overwrite) {
	var obj = {}, pairs = string.split('&'), d = decodeURIComponent, name, value;
	$.each(pairs, function(i, pair) {
		pair = pair.split('=');
		name = d(pair[0]);
		value = d(pair[1]);
		obj[name] = overwrite || !obj[name] ? value : [].concat(obj[name])
				.concat(value);
	});
	return obj;
};

/**
 * 获取指定表单的所有表单域的值,并包装在一个json对象中 return json
 */
UITag.getFormParamsToJson = function(formId) {
	return UITag.serializeToJson(UITag.getFormParamSerialize(formId));
}

/**
 * 获得grid选中行的数据,返回一个JSON数据对象格式的数据集合
 */
UITag.getGridCheckedRows = function(gridId) {
	return $("#datagrid_" + gridId).datagrid("getChecked");
}

/**
 * 获取编辑的数据
 */
UITag.getEditedData = function(gridId) {
	// 关闭所有编辑器
	for ( var i = 0; i < $("#datagrid_" + gridId).datagrid("getData").rows.length; i++) {
		try {
			$("#datagrid_" + gridId).datagrid('endEdit', i);
		} catch (e) {
		}
	}
	return $("#datagrid_" + gridId).datagrid('getChanges', 'updated');
}