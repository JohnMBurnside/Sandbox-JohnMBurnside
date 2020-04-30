function onclick()
{
    chrome.tabs.query
    (
        { currentWindow: true, active: true },
        function (tabs)
            { chrome.tabs.sendMessage(tabs[0].id, 'hi'); }
    );
} 
function onLoad()
{
    document.querySelector('button').addEventListener('click', onclick, false);
}
document.addEventListener('DOMContentLoaded', onLoad(), false);