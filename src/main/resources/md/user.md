sample
===
* 注释

	select #use("cols")# from user  where  #use("condition")#

cols
===
	id,create_time,update_time,phone,username,email,password,role

updateSample
===
	
	id=#id#,create_time=#createTime#,update_time=#updateTime#,phone=#phone#,username=#username#,email=#email#,password=#password#,role=#role#

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
	@if(!isEmpty(phone)){
	 and phone=#phone#
	@}
	@if(!isEmpty(username)){
	 and username=#username#
	@}
	@if(!isEmpty(email)){
	 and email=#email#
	@}
	@if(!isEmpty(password)){
	 and password=#password#
	@}
	@if(!isEmpty(role)){
	 and role=#role#
	@}
	
getUserByPhone
===
    SELECT id,phone,username,email,password,role FROM user WHERE phone=#phone#;
    
getUserByEmail
===
    SELECT id,phone,username,email,password,role FROM user WHERE email=#email#;