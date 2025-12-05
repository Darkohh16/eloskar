// Mobile Menu Hamburger Script
document.addEventListener('DOMContentLoaded', function() {
    let navToggle = null;
    let overlay = null;

    function initMobileMenu() {
        const header = document.querySelector('.header');
        const nav = document.querySelector('.nav');
        const navUser = document.querySelector('.nav-user');

        if (!nav || !navUser) return;

        if (window.innerWidth <= 768) {
            // Crear botón hamburguesa si no existe
            if (!document.querySelector('.nav-toggle')) {
                navToggle = document.createElement('div');
                navToggle.className = 'nav-toggle';

                // Crear las 3 líneas del hamburguesa
                for (let i = 0; i < 3; i++) {
                    const span = document.createElement('span');
                    navToggle.appendChild(span);
                }

                console.log('Botón hamburguesa creado con', navToggle.children.length, 'líneas');

                // Crear overlay
                overlay = document.createElement('div');
                overlay.className = 'nav-overlay';

                // Insertar después del logo
                const logoArea = document.querySelector('.logo-area');
                if (logoArea) {
                    logoArea.after(navToggle);
                    console.log('Botón hamburguesa insertado en el DOM');
                } else {
                    console.error('Logo area no encontrado');
                }
                document.body.appendChild(overlay);

                // Toggle menú
                navToggle.addEventListener('click', function() {
                    this.classList.toggle('active');
                    nav.classList.toggle('active');
                    navUser.classList.toggle('active');
                    overlay.classList.toggle('active');
                    document.body.style.overflow = this.classList.contains('active') ? 'hidden' : '';

                    // Actualizar posición después de abrir
                    if (this.classList.contains('active')) {
                        setTimeout(updateNavUserPosition, 100);
                    }
                });

                // Cerrar al hacer click en overlay
                overlay.addEventListener('click', function() {
                    closeMenu();
                });

                // Cerrar al hacer click en un link
                const allLinks = [...nav.querySelectorAll('a'), ...navUser.querySelectorAll('a'), ...navUser.querySelectorAll('button')];
                allLinks.forEach(link => {
                    link.addEventListener('click', function() {
                        closeMenu();
                    });
                });
            }
        } else {
            // Limpiar en desktop
            if (navToggle && navToggle.parentNode) {
                navToggle.remove();
                navToggle = null;
            }
            if (overlay && overlay.parentNode) {
                overlay.remove();
                overlay = null;
            }
            nav.classList.remove('active');
            navUser.classList.remove('active');
            document.body.style.overflow = '';
        }

        updateNavUserPosition();
    }

    function closeMenu() {
        const nav = document.querySelector('.nav');
        const navUser = document.querySelector('.nav-user');
        const toggleBtn = document.querySelector('.nav-toggle');
        const overlay = document.querySelector('.nav-overlay');

        if (toggleBtn) toggleBtn.classList.remove('active');
        if (nav) nav.classList.remove('active');
        if (navUser) navUser.classList.remove('active');
        if (overlay) overlay.classList.remove('active');
        document.body.style.overflow = '';
    }

    // Calcular altura del nav para posicionar nav-user
    function updateNavUserPosition() {
        if (window.innerWidth <= 768) {
            const nav = document.querySelector('.nav');
            if (nav) {
                // Esperar a que el nav esté visible para calcular altura real
                setTimeout(() => {
                    const actualHeight = nav.scrollHeight || nav.offsetHeight;
                    document.documentElement.style.setProperty('--nav-height', actualHeight + 'px');
                    console.log('Nav height set to:', actualHeight + 'px');
                }, 100);
            }
        }
    }

    // Inicializar
    initMobileMenu();

    // Re-inicializar en resize con debounce
    let resizeTimer;
    window.addEventListener('resize', function() {
        clearTimeout(resizeTimer);
        resizeTimer = setTimeout(function() {
            initMobileMenu();
        }, 250);
    });
});

