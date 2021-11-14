const avatar = document.querySelector('.avatar');
const username = document.querySelector('.username');

const nameUser = document.querySelector('.name');
const loginUser = document.querySelector('.loginUser');
const followersUser = document.querySelector('.followers');
const followingUser = document.querySelector('.following');
const bioUser = document.querySelector('.bio');

function getUser() {
    fetch(`https://api.github.com/users/${username.value}`)
        .then((response) => response.json())
        .then((data) => {
            console.log(data.json);
            avatar.src = data.avatar_url;
            loginUser.innerText = data.login;
            nameUser.innerText = data.name;
            followersUser.innerText = data.followers;
            followingUser.innerText = data.following;
            bioUser.innerText = data.bio;
        })
        .catch((erro) => console.log(erro))
}
