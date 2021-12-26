sample
===
* 注释

	select #use("cols")# from car_rental_order  where  #use("condition")#

cols
===
	id,create_time,update_time,order_no,user_id,car_id,state,start_time,end_time,expire_time,count

updateSample
===
	
	id=#id#,create_time=#createTime#,update_time=#updateTime#,order_no=#orderNo#,user_id=#userId#,car_id=#carId#,state=#state#,start_time=#startTime#,end_time=#endTime#,expire_time=#expireTime#,count=#count#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(createTime)){
	 and create_time=#createTime#
	@}
	@if(!isEmpty(updateTime)){
	 and update_time=#updateTime#
	@}
	@if(!isEmpty(orderNo)){
	 and order_no=#orderNo#
	@}
	@if(!isEmpty(userId)){
	 and user_id=#userId#
	@}
	@if(!isEmpty(carId)){
	 and car_id=#carId#
	@}
	@if(!isEmpty(state)){
	 and state=#state#
	@}
	@if(!isEmpty(startTime)){
	 and start_time=#startTime#
	@}
	@if(!isEmpty(endTime)){
	 and end_time=#endTime#
	@}
	@if(!isEmpty(expireTime)){
	 and expire_time=#expireTime#
	@}
	@if(!isEmpty(count)){
	 and count=#count#
	@}
	
updateOrderStateByOrderNo
===
    UPDATE 	car_rental_order SET state=#state# WHERE order_no = #orderNo#;

updateOrderEndTimeByOrderNo
===
    UPDATE 	car_rental_order SET end_time=#date# WHERE order_no = #orderNo#;
    
getOrderInfoByOrderNo
===
    SELECT  #use("cols")# FROM car_rental_order  WHERE order_no = #orderNo#;

updateOrderStateCancelByOutTime
===
    UPDATE car_rental_order SET state=2 WHERE order_no in (#join(orderNos)#);
    
getOrderStateCancelByOutTime
===
    SELECT #use("cols")# From car_rental_order WHERE state=0 AND expire_time>=NOW();