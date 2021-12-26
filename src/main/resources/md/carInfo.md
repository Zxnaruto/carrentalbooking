sample
===
* 注释

	select #use("cols")# from car_info  where  #use("condition")#

cols
===
	id,create_time,update_time,car_name,car_type,state,car_stock,rent

updateSample
===
	
	id=#id#,create_time=#createTime#,update_time=#updateTime#,car_name=#carName#,car_type=#carType#,state=#state#,car_stock=#carStock#,rent=#rent#

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
	@if(!isEmpty(carName)){
	 and car_name=#carName#
	@}
	@if(!isEmpty(carType)){
	 and car_type=#carType#
	@}
	@if(!isEmpty(state)){
	 and state=#state#
	@}
	@if(!isEmpty(carStock)){
	 and car_stock=#carStock#
	@}
	@if(!isEmpty(rent)){
	 and rent=#rent#
	@}
	
getCarInfoByCarName
===
    SELECT #use("cols")# FROM car_info  WHERE car_name=#carName#;

upAndDownCarInfo
===
    UPDATE car_info SET state=#state# WHERE id=#carId#;
 
addCarStock
===
    UPDATE car_info SET car_stock = car_stock + 1 WHERE id = #carId#;

subCarStock
===
    UPDATE car_info SET car_stock = car_stock - 1 WHERE id = #carId# AND car_stock > 0;

getAllUpStateCarInfo
===
    SELECT id,car_name,car_stock,rent FROM car_info WHERE car_stock > 0; 	