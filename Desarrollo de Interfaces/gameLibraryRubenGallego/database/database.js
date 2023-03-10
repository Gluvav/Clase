const mongoose = require('mongoose');

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://127.0.0.1:27017/gameLibrary', {
    useNewUrlParser: true,
    useUnifiedTopology: true
});

let gamesSchema = new mongoose.Schema({
    id: {
        type: Number,
        required: true,
        minlength: 1,
        unique: true
    },
    name: {
        type: String,
        required: true,
        minlength: 1,
    },
    developer: {
        type: String,
        required: true,
    },
    synopsis: {
        type: String,
        trim: true
    },
    launcYear: {
        type: Number,
        required: true,
        trim: true
    },
    img: {
        type: String
    }
});

let Game = mongoose.model('gameLibrary', gamesSchema);

module.exports = {
    Game: Game
}