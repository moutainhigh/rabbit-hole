function getUrlParam(name) {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    let r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function goPage(isLoginPage) {
    let title = isLoginPage ? '登陆' : '注册';
    let params = {
        action: isLoginPage ? 'sign-in' : 'sign-up'
    }
    history.replaceState(params, title, '?' + $.param(params));
}

$(function () {
    const sign_in_btn = document.querySelector("#sign-in-btn");
    const sign_up_btn = document.querySelector("#sign-up-btn");
    const container = document.querySelector(".container");

    sign_up_btn.addEventListener("click", () => {
        goPage(false);
        hashChangeFire();
    });

    sign_in_btn.addEventListener("click", () => {
        goPage(true);
        hashChangeFire();
    });

    let hashChangeFire = function () {
        let action = getUrlParam('action') ?? 'sign-in';
        let isLogin = action === 'sign-in';
        if (!isLogin) {
            container.classList.add("sign-up-mode");
        } else {
            container.classList.remove("sign-up-mode");
        }
    }

    hashChangeFire();
});
