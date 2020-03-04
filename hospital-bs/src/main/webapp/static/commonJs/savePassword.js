var SavePassword={
    //用户名-对象ID
    userNameBtn:'#LAY-user-login-username',
    //密码-对象ID
    passwordBtn:'#LAY-user-login-password',
    //是否保存密码-对象ID
    rememberBtn:'#LAY-user-login-checkbox',
    //保存天数
    days:7,
    onLogin:function(){
        //保存用户名n天
        jQuery.cookie('username',jQuery(this.userNameBtn).val(), { expires: this.days });

        var isRemember=jQuery(this.rememberBtn).prop('checked');
        if(isRemember){
            jQuery.cookie(jQuery(this.userNameBtn).val(), jQuery(this.passwordBtn).val(), { expires: this.days });
            jQuery.cookie('RememberMe', 'true', { expires: this.days });
        }else{
            jQuery.cookie(jQuery(this.userNameBtn).val(), null);
            jQuery.cookie('RememberMe', null);
        }
    },
    autoFillIn:function(form){
        var userName=jQuery.cookie('username');
        if(userName!==undefined){
            jQuery(this.userNameBtn).val(userName);
            var isRemember=jQuery.cookie('RememberMe');
            if(isRemember=='true'){
                var password=jQuery.cookie(userName);
                if(password!==undefined){
                    jQuery(this.passwordBtn).val(password);
                }
                jQuery(this.rememberBtn).prop('checked',true);
                form ? form.render("checkbox") : null;
            }
        }
    }
};