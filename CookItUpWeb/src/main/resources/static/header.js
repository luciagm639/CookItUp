let header = document.createElement("header");

let cookitup = document.createElement("a");
cookitup.href = "/home.html";
let h1 = document.createElement("h1");
h1.textContent = "CookItUp!";
cookitup.appendChild(h1);

let logIn = document.createElement("a");
logIn.href = "/logIn.html";
let logInButton = document.createElement("button");
logInButton.textContent = "Log in";
logIn.appendChild(logInButton);

let signUp = document.createElement("a");
signUp.href = "/signUp.html";
let signUpButton = document.createElement("button");
signUpButton.textContent = "Sign up";
signUp.appendChild(signUpButton);


let hr = document.createElement("hr");

header.appendChild(cookitup);
header.appendChild(logIn);
header.appendChild(signUp);

document.body.appendChild(header);
document.body.appendChild(hr);

const fetchPromise = fetch('/user/current');

fetchPromise.then( response => {
   const jsonPromise = response.json();
   jsonPromise.then( user => {
       console.log(user);

       let createRecipe = document.createElement("a");
       createRecipe.href = "/recipe/create.html";
       let createRecipeButton = document.createElement("button");
       createRecipeButton.textContent = "Create a recipe";
       createRecipe.appendChild(createRecipeButton);

       let viewOwnRecipes = document.createElement("a");
       let viewOwnRecipeButton = document.createElement("button");
       viewOwnRecipeButton.textContent = "View own recipes";
       viewOwnRecipes.appendChild(viewOwnRecipeButton);
       viewOwnRecipes.href = "/user/"+user.id+"/recipes.html";

       header.appendChild(createRecipe);
       header.appendChild(viewOwnRecipes);
   });
});




