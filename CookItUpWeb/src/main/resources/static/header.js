let header = document.createElement("header");

let cookitup = document.createElement("a");
cookitup.href = "/home";
let logo = new Image();
logo.height = 200;
logo.src = "/LogoCookItUp.png";
cookitup.appendChild(logo);

let logIn = document.createElement("a");
logIn.href = "/log_in";
let logInButton = document.createElement("button");
logInButton.textContent = "Log in";
logIn.appendChild(logInButton);

let signUp = document.createElement("a");
signUp.href = "/sign_up";
let signUpButton = document.createElement("button");
signUpButton.textContent = "Sign up";
signUp.appendChild(signUpButton);

let search = document.createElement("a");
search.href = "/search_recipes";
let searchButton = document.createElement("button");
searchButton.textContent = "Search recipes";
search.appendChild(searchButton);

let hr = document.createElement("hr");

header.appendChild(cookitup);
header.appendChild(logIn);
header.appendChild(document.createTextNode(" "));
header.appendChild(signUp);
header.appendChild(document.createTextNode(" "));
header.appendChild(search);

document.body.appendChild(header);
document.body.appendChild(hr);

const fetchPromise = fetch('/user/current');

fetchPromise.then( response => {
    return response.json();
}).then( user => {
   let createRecipe = document.createElement("a");
   createRecipe.href = "/create_recipe";
   let createRecipeButton = document.createElement("button");
   createRecipeButton.textContent = "Create a recipe";
   createRecipe.appendChild(createRecipeButton);

   let viewOwnRecipes = document.createElement("a");
   let viewOwnRecipeButton = document.createElement("button");
   viewOwnRecipeButton.textContent = "View own recipes";
   viewOwnRecipes.appendChild(viewOwnRecipeButton);
   viewOwnRecipes.href = "/user/"+user.id+"/view_recipes";

   let viewOwnProfile = document.createElement("a");
   let viewOwnProfileButton = document.createElement("button");
   viewOwnProfileButton.textContent = "View own profile";
   viewOwnProfile.appendChild(viewOwnProfileButton);
   viewOwnProfile.href = "/user/"+user.id+"/profile";

   header.appendChild(document.createTextNode(" "));
   header.appendChild(createRecipe);
   header.appendChild(document.createTextNode(" "));
   header.appendChild(viewOwnRecipes);
   header.appendChild(document.createTextNode(" "));
   header.appendChild(viewOwnProfile);
}).catch( error => { console.log("There isn't a user logged in") });




