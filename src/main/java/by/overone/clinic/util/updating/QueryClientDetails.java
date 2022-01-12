package by.overone.clinic.util.updating;

import by.overone.clinic.model.ClientDetails;

//    public static final String UPDATE_CLIENT_DETAILS_QUERY = "UPDATE client_details SET name=?, surname=?, address=?, " +
//            "data_birth=?, phone_number=? WHERE client_user_id=?";

public class QueryClientDetails {
    public String createQuery(ClientDetails newClientDetails, ClientDetails oldClientDetails) {
        StringBuilder sql = new StringBuilder("UPDATE client_details SET ");

        if (!newClientDetails.getName().equals(oldClientDetails.getName())) {
            sql.append("name=?");
        }
        if (!newClientDetails.getSurname().equals(oldClientDetails.getSurname())) {
            sql.append("surname=?");
        }
        if (!newClientDetails.getAddress().equals(oldClientDetails.getAddress())) {
            sql.append("address=?");
        }
        if (!newClientDetails.getDataBirth().equals(oldClientDetails.getDataBirth())) {
            sql.append("data_birth=?");
        }
        if (!newClientDetails.getPhoneNumber().equals(oldClientDetails.getPhoneNumber())) {
            sql.append("phone_number=?");
        }

        String sql1 = sql.toString().replace("?", "?, ");
        String sql2 = sql1.substring(0,sql1.length() - 2);

        return sql2;
    }
}
