function notiSound() {
    const audio = new Audio("/notisound/the-noti-123.mp3");

    audio.play()
        .then(() => {
            console.log('Audio started playing');
        })
        .catch(error => {
            console.error('Error playing audio:', error.message);
        });
}

export default notiSound;