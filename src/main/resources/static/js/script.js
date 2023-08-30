    const audio = document.getElementById('audio');
    const audioSource = document.getElementById('audioSource');
    const listSongs = document.getElementById('songs');
    var songs = [];

    window.onload = () => {
       const songNames =  fetch('http://localhost:8080/api/mp3/songs-names')
        .then(response => {
            return response.json()
        })

        const printSongsNames = () => {
            songNames.then((result) => {
                songs = result
                createListSongs(songs)
            });
        };

        printSongsNames();
    }

    document.addEventListener("click", function(e){
        const target = e.target.closest(".playBtn"); // Or any other selector.

        if(target){
            playSong(target.getAttribute('data-song-name'))
        }
    });

    function createListSongs(songs) {
        songs.forEach(songName => {
            createBlockSong(songName)
        });
    }

    function createBlockSong(songName) {
        var block = `
        <div class="song-container">
            <div class="song-container-2">
                <div class="song-subcontainer">
                    <img class="note-icon" src="musical-note.png">
                    <span>${songName}</span>
                </div>
                <div class="actions-song"><button class="playBtn" data-song-name='${songName}'><img class="play-song" src="play.png"></button></div>
            </div>
            <hr>
        </div>`
        listSongs.insertAdjacentHTML("beforeend", block)
    }

    function loadSong(songName) {
        audioSource.src = `http://localhost:8080/api/mp3/song/${songName}`;
        audio.load();
    }

    /*stopBtn.addEventListener('click', () => {
        audio.pause();
        audio.currentTime = 0;
    });

    backBtn.addEventListener('click', () => {
        if (currentSongIndex > 0) {
            currentSongIndex--;
        } else {
            currentSongIndex = songs.length - 1;
        }
        loadSong(currentSongIndex);
        audio.play();
    });

    nextBtn.addEventListener('click', () => {
        if (currentSongIndex < songs.length - 1) {
            currentSongIndex++;
        } else {
            currentSongIndex = 0;
        }
        loadSong(currentSongIndex);
        audio.play();
    }); */

    function playSong(songName) {
        loadSong(songName);
        audio.play();
    }
