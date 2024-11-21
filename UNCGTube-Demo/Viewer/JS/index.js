let userName = 'UNCG Student';  // Declaring a variable
const school = 'UNCG';  // Constant value

function greetUser() {
    console.log('Hello, ' + userName + '! Welcome to ' + school + 'Tube.');
}

function updatePageTitle() {
    let pageTitle = document.getElementById('pageTitle');
    pageTitle.textContent = 'Welcome, ' + userName;
}

function setupButtonListener() {
    let button = document.getElementById('greetButton');
    button.addEventListener('click', function() {
        alert('Hello, ' + userName + '! Thanks for visiting ' + school + 'Tube.');
    });
}

greetUser();
updatePageTitle();
setupButtonListener();
