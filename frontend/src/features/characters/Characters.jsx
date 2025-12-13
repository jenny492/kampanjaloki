import axios from 'axios';

let baseUrl = import.meta.env.VITE_API_URL;

export const getCharacters = async () => {
    try {
        const response = await axios.get(baseUrl + 'characters');
        return response;
    } catch (error) {
        return ({ status: error.status, message: 'Haku ei onnistunut: ' + error.message });
    }
}

export const createCharacter = async (character) => {
    try {
        const response = await axios.post(baseUrl + 'characters', character, {
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        });
        return (response);
    } catch (error) {
        return ({ status: error.status, message: 'Lisäys ei onnistunut: ' + error.message });
    }
}

export const updateCharacter = async (id, character) => {
    try {
        const response = await axios.put(baseUrl + 'characters/' + id, character, {
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        });
        return (response);
    } catch (error) {
        return ({ status: error.status, message: 'Muokkaus ei onnistunut: ' + error.message });
    }
}

export const deleteCharacter = async (id) => {
    try {
        const response = await axios.delete(baseUrl + 'characters/' + id);
        return (response);
    } catch (error) {
        return ({ status: error.status, message: 'Poisto ei onnistunut: ' + error.message });
    }
}