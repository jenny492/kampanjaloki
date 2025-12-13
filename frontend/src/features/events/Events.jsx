import axios from 'axios';

let baseUrl = import.meta.env.VITE_API_URL;

export const getEvents = async () => {
    try {
        const response = await axios.get(baseUrl + 'events');
        return response;
    } catch (error) {
        return ({ status: error.status, message: 'Haku ei onnistunut: ' + error.message });
    }
}

export const createEvent = async (event) => {
    try {
        const response = await axios.post(baseUrl + 'events', event, {
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
    });
    return (response);
  } catch (error) {
    return ({ status: error.status, message: 'Lisäys ei onnistunut: ' + error.message });
  }
}