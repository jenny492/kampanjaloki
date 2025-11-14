function CampaignList({ campaigns }) {

    return (
        
        <div>
            <h2>Kampanjat</h2>
            {
                campaigns.map(campaign => {
                    return (
                        <p key={campaign.id}>
                            {campaign.name}<br />
                            Kuvaus: {campaign.description}
                        </p>
                                   
                );
            })
        }
        </div>
    );
}

export default CampaignList;