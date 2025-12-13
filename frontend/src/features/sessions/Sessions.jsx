import axios from 'axios';

let baseUrl = import.meta.env.VITE_API_URL;

export const getSessions = async () => {
    try {
        const response = await axios.get(baseUrl + 'sessions');
        return response;
    } catch (error) {
        return ({ status: error.status, message: 'Haku ei onnistunut: ' + error.message });
    }
}

export const createSession = async (session) => {
    try {
        const response = await axios.post(baseUrl + 'sessions', session, {
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    });

    return (response);
  } catch (error) {
    return ({ status: error.status, message: 'Lisäys ei onnistunut: ' + error.message });
  }
}