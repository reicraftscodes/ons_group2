function addSkill() {
    let xhr = new XMLHttpRequest();

    let title = document.querySelector("#skillName");
    let description = document.querySelector("#description");
    let confidence = document.querySelector("#confidence");

    let payload = {
        "title": title.value,
        "description": description.value,
        "confidence": confidence.value
    };

    xhr.open('POST', '/api/Skills/AddSkill');
    xhr.setRequestHeader(csrfHeaderName, csrfToken);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(payload));
}

function removeSkill(id) {
    let xhr = new XMLHttpRequest();

    xhr.open('DELETE', `/api/Skills/RemoveSkill/${id}`);
    xhr.setRequestHeader(csrfHeaderName, csrfToken);
    xhr.send();
}