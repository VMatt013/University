const container = document.getElementById("container");

function addItem(event){
    const item = document.createElement("div");
    item.className = "item";
    item.innerHTML = container.children.length+1;
    container.append(item);
}

function removeItems(event){
    while(container.firstChild){
        container.removeChild(container.firstChild)
    }
}

function changeFlexDirection(event){
    const value = event.currentTarget.value
    console.log(`flex is set to ${value}`)
    container.style.flexDirection = value
}

function changeJustifyContent(event){
    const value = event.currentTarget.value
    console.log(`justify content is set to ${value}`)
    container.style.justifyContent = value
}

function changeAligContent(event){
    const value = event.currentTarget.value
    console.log(`align content is set to ${value}`)
    container.style.alignContent = value
}


document.getElementById("add-item-btn").addEventListener('click',addItem)
document.getElementById("remove-item-btn").addEventListener('click',removeItems)
document.getElementById("flex-direction-select").addEventListener('change',changeFlexDirection)
document.getElementById("justify-content-select").addEventListener('change',changeJustifyContent)
document.getElementById("align-items-select").addEventListener('change',changeAligContent)
