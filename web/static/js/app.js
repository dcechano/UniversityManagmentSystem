let links = document.getElementsByClassName("nav-link");
for (var i = 0; i < links.length; i++) {
    links[i].addEventListener('mouseover', function () {
        this.style.backgroundColor = 'rgb(4, 76, 30)';
        this.style.color = 'white';
        this.style.borderRadius = '0.3rem';
        this.style.transition = '0.2s';
    });
    links[i].addEventListener('mouseout', function () {
        this.style.backgroundColor = '#f8f9fa';
        this.style.color = 'black';
        this.style.transition = '0.2s';
    });
}
