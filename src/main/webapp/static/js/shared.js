var backdrop=document.querySelector(".backdrop");
var modal=document.querySelector(".login-modal");
var loginButton=document.querySelector(".main-nav__items [type=loginButton]");
var devNav=document.querySelector(".dev-nav");
var devButton=document.querySelector(".dev-button");

loginButton.addEventListener("click", function(){
    modal.classList.add("open");
    backdrop.classList.add("open");
});

devButton.addEventListener("click", function(){
    devNav.classList.add("open");
    backdrop.classList.add("open");
});



backdrop.addEventListener("click", function(){
    modal.classList.remove("open");
    devNav.classList.remove("open");
    backdrop.classList.remove("open");
});



