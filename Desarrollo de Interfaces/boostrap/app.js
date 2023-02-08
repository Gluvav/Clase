const { Task } = require('./database/database');
const database = require('./database/database');

let addTask = document.getElementById('addTask');
let deleteTask = document.getElementById('deleteTask');
let updateTask = document.getElementById('updateTask');

let list = document.getElementById('lista');

let taskInfo;

addTask.addEventListener('click', () => {
    taskInfo = document.getElementById('taskInfo').value;
    console.log("event listener, " + taskInfo)
    let Task = new database.Task({
        name: taskInfo
    });
    Task.save().then(resu => {
        console.log("save")
        //list.insertAdjacentHTML('beforeend', `<li class="list-group-item">${taskInfo}</li>`);
        list.innerHTML = '<li class="list-group-item">' + resu.name + '</li>';
        document.getElementById('taskInfo').value = '';
        findTasks();
    }).catch(err => {
        console.log(err);
    });
});

let findTasks = () => {
    Task.find().then(resu => {
        printTasks(resu);
    });
}

let findEdit = () => {
    Task.find().then(resu => {
        printEdit(resu);
    });
}

let findDelete = () => {
    Task.find().then(resu => {
        printDelete(resu);
    });
}

findTasks();

let printTasks = (tasks => {
    let a = "";
    for (let i = 0; i < tasks.length; i++) {
        a += "<li class='list-group-item' id='li" + i + "'>" + tasks[i].name + "</li>";
    }
    list.innerHTML = a;
});

let printEdit = (tasks => {
    let a = "";
    for (let i = 0; i < tasks.length; i++) {
        a += "<li class='list-group-item' id='li" + i + "'>";
        a += "<input class='form-control' id='taskName" + i + "' value='" + tasks[i].name + "'></input>";
        a += "<button type='button' class='btn btn-secondary' id='btnUpdate" + i + "'>Actualizar</button>";
        a += "</li>";
    }
    list.innerHTML = a;
    for (let i = 0; i < tasks.length; i++) {
        //llamar funcion que hace el update pasandole el i
        let a = document.getElementById('taskName' + i).textContent;
        updateTasks(i);
    }
});

let printDelete = (tasks => {
    let a = "";
    for (let i = 0; i < tasks.length; i++) {
        //a += "<li class='list-group-item' id='li" + i + "'>" + tasks[i].name + "</li>";
        a += "<li class='list-group-item' id='li" + i + "'>";
        a += "<button class='btn btn-danger' id='task" + i + "'>delete</button><label id='txt" + i + "'>" + tasks[i].name + "</label>";
        a += "</li>";
    }
    list.innerHTML = a;
    for (let i = 0; i < tasks.length; i++) {
        deleteTasks(i);
    }
});

function deleteTasks(i) {
    document.getElementById('task' + i).addEventListener('click', () => {
        let a = document.getElementById('txt' + i).textContent;
        console.log(a);
        Task.find({ name: a }).then(resu => {
            console.log(resu.toString());
        }).catch(err => {
            console.log(err);
        });
        Task.findOneAndDelete({ name: a }).then(resu => {
            findTasks();
        }).catch(err => {
            console.log(err);
        })
    });
}

function updateTasks(i) {
    let str = document.getElementById('taskName' + i).value;
    document.getElementById('btnUpdate' + i).addEventListener('click', () => {
        let a = document.getElementById('taskName' + i).value;
        console.log(a);
        /*Task.find({name: a}).then(resu => {
            console.log(resu.toString());
        }).catch(err => {
            console.log(err);
        });*/
        console.log(str);
        Task.findOneAndUpdate({ name: str }, { name: a }, {new: true}).then(resu => {
            console.log('test')
            findTasks();
        }).catch(err => {
            console.log(err);
        });
        /*Task.findOneAndDelete({ name: a }).then(resu => {
            findTasks();
        }).catch(err => {
            console.log(err);
        });*/
    });
}

deleteTask.addEventListener('click', () => {
    findDelete();
});

updateTask.addEventListener('click', () => {
    findEdit();
})