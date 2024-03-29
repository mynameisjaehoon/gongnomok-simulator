export function playFailureSound() {
  let failMp3 = new Audio('/sound/fail.mp3');
  failMp3.volume = 0.15
  failMp3.currentTime = 0;
  failMp3.play();
}

export function playSuccessSound() {
  let failMp3 = new Audio('/sound/success.mp3');
  failMp3.volume = 0.15
  failMp3.currentTime = 0;
  failMp3.play();
}

export function playPurchaseSound() {
  let sound = new Audio('/sound/purchase.mp3');
  sound.volume = 0.15;
  sound.currentTime = 0;
  sound.play();
}

export function playDiceSound() {
  let sound = new Audio('/sound/dice.mp3');
  sound.volume = 0.20;
  sound.currentTime = 0;
  sound.play();
}