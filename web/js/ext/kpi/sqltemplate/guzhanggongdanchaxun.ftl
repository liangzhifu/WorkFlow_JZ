SELECT DFO.ID                   AS id,
       DFO.ORDER_TITLE          AS order_title,
       DFO.ORDER_CODE           AS order_code,
       DFO.CREATE_DATE          AS create_date,
       DFO.CREATE_STAFF_ID      AS create_staff_id,
       DFO.CREATE_STAFF_NAME    AS create_staff_name,
       DFO.CREATE_ORG_ID        AS create_org_id,
       DFO.CREATE_ORG_NAME      AS create_org_name,
       DFO.FAULT_DES            AS fault_des,
       DFO.FAULT_TYPE           AS fault_type,
       DFO.FAULT_TYPE_NAME      AS fault_type_name,
       DFO.FAULT_POINTS         AS fault_points,
       DFO.FAULT_POINTS_NAME    AS fault_points_name,
       DFO.RECEIVE_IDS          AS receive_ids,
       DFO.RECEIVE_NAMES        AS receive_names,
       DFO.FAULT_LEVEL          AS fault_level,
       DFO.FAULT_LEVEL_NAME     AS fault_level_name,
       DFO.REQ_FINISH_TIMES     AS req_finish_times,
       DFO.REQ_FINISH_DATE      AS req_finish_date,
       DFO.ORDER_ID             AS order_id,
       DFO.VERSION              AS version,
       DFO.ORDER_STATE          AS order_state,
       DFO.ORDER_STATE_TEXT     AS order_state_text,
       DFO.PROCESSINSTANCEID    AS processinstanceid,
       DFO.STATE                AS state,
       DFO.OLD_ORDER_STATE      AS old_order_state,
       DFO.OLD_ORDER_STATE_TEXT AS old_order_state_text
  FROM DW_FAULT_ORDER DFO
  WHERE 1 = 1 
  <#if orderId??>
   AND DFO.ORDER_ID LIKE ${"'%"+orderId+"%'"}
  </#if>  
  <#if title??>
   AND DFO.ORDER_TITLE LIKE ${"'%"+title+"%'"}
  </#if>  
  <#if orderCode??>
   AND DFO.ORDER_CODE LIKE ${"'%"+orderCode+"%'"}
  </#if>  
  <#if reqfinishdate??>
   AND DFO.REQ_FINISH_DATE BETWEEN TO_DATE(${"'"+reqfinishdate+"'"},'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(${"'"+reqfinishdateend+"'"},'YYYY-MM-DD HH24:MI:SS')
  </#if>  
  <#if faultLevelName??>
   AND DFO.FAULT_LEVEL_NAME LIKE ${"'%"+faultLevelName+"%'"}
  </#if>  
  <#if receiverNames??>
   AND DFO.RECEIVE_NAMES LIKE ${"'%"+receiverNames+"%'"}
  </#if>  
