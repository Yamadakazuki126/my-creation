const form = document.getElementById("form");
const input = document.getElementById("input");
const ul = document.getElementById("ul");

const todos = JSON.parse(localStorage.getItem("todos")) || [];

if (todos) { // 空の場合はfalse
  todos.forEach(todo => {
    add(todo);
  });
}

form.addEventListener("submit", function (event) {
  event.preventDefault();
  add();
});

function add(todo) {
  let todoText = input.value;

  if (todo) {
    todoText = todo.text;
  }

  if (todoText) { // 空文字はfalse
    const li = document.createElement("li");
    li.innerText = todoText;
    li.classList.add("list-group-item");
    li.style.position = "relative"; // 削除ボタン用

    if (todo && todo.completed) {
      li.classList.add("text-decoration-line-through");
    }

    // 削除ボタンを作成
    const deleteButton = document.createElement("button");
    deleteButton.innerText = "削除";
    deleteButton.classList.add("btn", "btn-danger", "btn-sm");
    deleteButton.style.position = "absolute";
    deleteButton.style.top = "50%";
    deleteButton.style.right = "10px";
    deleteButton.style.transform = "translateY(-50%)";
    deleteButton.style.display = "none";

    deleteButton.addEventListener("click", function () {
      li.remove();
      saveData();
    });

    // ホバー時に削除ボタンを表示
    li.addEventListener("mouseenter", function () {
      deleteButton.style.display = "block";
    });

    // ホバーが外れたら削除ボタンを非表示
    li.addEventListener("mouseleave", function () {
      deleteButton.style.display = "none";
    });

    li.addEventListener("click", function () {
      li.classList.toggle("text-decoration-line-through");
      saveData();
    });

    li.appendChild(deleteButton);
    ul.appendChild(li);
    input.value = "";
    saveData();
  }
}

function saveData() {
  const lists = document.querySelectorAll("li");
  let todos = [];

  lists.forEach(list => {
    let todo = {
      text: list.childNodes[0].nodeValue.trim(), // テキストのみ取得
      completed: list.classList.contains("text-decoration-line-through")
    };
    todos.push(todo);
  });

  localStorage.setItem("todos", JSON.stringify(todos));
}
