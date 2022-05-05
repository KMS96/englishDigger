const wordSelects = document.getElementsByTagName('li'); // This is your UL element

function showTranslate(e) {
    // e.target.classList.add('song-selection');
    // e.firstChild.nodeValue = 'newValue';
    var thff = [[${englishWord.russianTranslate}]];
    console.log(thff);
    console.log(e.valueOf().data = '${englishWord.russianTranslate}');
}
console.log(wordSelects);

for (let i = 0; i < wordSelects.length; i++) {
    wordSelects[i].addEventListener('click', showTranslate);
}