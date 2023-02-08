const mongoose = require('mongoose');

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost:27017/tasksTarde', {
    useNewUrlParser: true,
    useUnifiedTopology: true
});

//eschema
let tasksSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    }
});

//model
let Task = mongoose.model('tasksTarde', tasksSchema);

module.exports ={
    Task: Task
}