import axios from 'axios';

let baseUrl = import.meta.env.VITE_API_URL;

export const getCampaigns = async () => {
    try {
        const response = await axios.get(baseUrl + 'campaigns');
        return response;
    } catch (error) {
        return ({ status: error.status, message: 'Haku ei onnistunut: ' + error.message });
    }
}

export const getCampaignById = async (id) => {
    try {
        const response = await axios.get(baseUrl + 'campaigns/' + id);
        return response;
    } catch (error) {
        return ({ status: error.status, message: 'Haku ei onnistunut: ' + error.message });
    }
}

export const createCampaign = async (campaign) => {
    try {
        const response = await axios.post(baseUrl + 'campaigns', campaign, {
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        });
        return (response);
    } catch (error) {
        return ({ status: error.status, message: 'Lisäys ei onnistunut: ' + error.message });
    }
}

export const updateCampaign = async (id, campaign) => {
    try {
        const response = await axios.put(baseUrl + 'campaigns/' + id, campaign, {
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        });
        return (response);
    } catch (error) {
        return ({ status: error.status, message: 'Muokkaus ei onnistunut: ' + error.message });
    }
}

export const deleteCampaign = async (id) => {
    try {
        const response = await axios.delete(baseUrl + 'campaigns/' + id);
        return (response);
    } catch (error) {
        return ({ status: error.status, message: 'Poisto ei onnistunut: ' + error.message });
    }
}
