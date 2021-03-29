export const PORT = process.env.PORT || 5000; // Default port must match pom.xml
const API_HOST = `/api`

export const API_ONEPROJECT = API_HOST + "/project/";
export const API_ALLPROJECTS = API_HOST + "/all/";
export const API_ADDPROJECT = API_HOST + "/addproject/";

export const API_ALLTICKETS = API_HOST + "/alltickets/";
export const API_ALLTICKETFORPROJECT = API_HOST + "/project/ticket/"
export const API_ONETICKET = API_HOST + "/ticket/";
export const API_ADDTICKET = API_HOST + "/addticket/";