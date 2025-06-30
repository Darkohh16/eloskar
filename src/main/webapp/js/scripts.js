document.addEventListener("DOMContentLoaded", () => {
    const cP = document.body.dataset.contextPath;

    fetch(cP + "/jsp/dashboardJSP/MenuLateral.jsp")
        .then(response => response.text())
        .then(data => {
            const menuContainer = document.getElementById('menu-lateral');
            if (menuContainer) {
                menuContainer.innerHTML = data;

                const links = menuContainer.querySelectorAll('a');
                const currentPage = location.pathname.split('/').pop();
                links.forEach(link => {
                    if (link.getAttribute('href')?.includes(currentPage)) {
                        link.classList.add('active');
                    }
                });
            }
        });
});
