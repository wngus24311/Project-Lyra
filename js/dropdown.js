let optionMenu = document.querySelector(".select-menu"), 
    selectBtn = document.querySelector(".select-btn"),
    options = document.querySelectorAll(".option"),
    sBtn_text = document.querySelector(".sBtn-text");
    console.log("타나안타나");
    selectBtn.addEventListener("mouseover", () => optionMenu.classList.toggle("active"));
    
    options.forEach(option => {
        option.addEventListener("click", ()=>{
            let selectedOption = option.querySelector(".option-text").innerText;
            sBtn_text.innerText = selectedOption;
    
            optionMenu.classList.remove("active");
    
        })
    
    })

    console.log("타나안타나");