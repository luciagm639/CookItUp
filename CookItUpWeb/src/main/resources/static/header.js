let header = document.createElement("header");

let cookitup = document.createElement("a");
cookitup.href = "/home.html";
let h1 = document.createElement("h1");
h1.textContent = "CookItUp!";
cookitup.appendChild(h1);

let createRecipe = document.createElement("a");
createRecipe.href = "/recipe/create";
let createRecipeButton = document.createElement("button");
createRecipeButton.textContent = "Create a recipe"
createRecipe.appendChild(createRecipeButton);

let viewOwnRecipes = document.createElement("a");
let viewOwnRecipeButton = document.createElement("button");
viewOwnRecipeButton.textContent = "View own recipes";
viewOwnRecipes.appendChild(viewOwnRecipeButton);

let hr = document.createElement("hr");

header.appendChild(cookitup);

document.body.appendChild(header);
document.body.appendChild(hr);

const fetchPromise = fetch('/user/own_recipes');

fetchPromise.then( response => {
    console.log(response);
   const jsonPromise = response.json();
   jsonPromise.then( json => {
       console.log(json)

       let url = json;
       if (url != "") {
           console.log(url);
           viewOwnRecipes.href = url;

           header.appendChild(createRecipe);
           header.appendChild(viewOwnRecipes);
       }

   });
});




