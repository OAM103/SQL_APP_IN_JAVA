package sample;

import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnection;


    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://"+dbHost+":"+
                dbPort+"/"+dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);

        return dbConnection;
    }

    public ResultSet getMasters(){ //Функция возвращения мастера

        ResultSet resSetMas  = null;
        Master master = new Master();
        AppClientController2 ACC2 = new AppClientController2();

        String Masters = "SELECT * FROM " + Const.MASTER_TABLE+";";

        try{
            Statement PSMas = getDbConnection().prepareStatement(Masters);

            resSetMas  = PSMas.executeQuery(Masters);
            while(resSetMas.next()) {
                master.setID(resSetMas.getInt(1));
                master.setName(resSetMas.getString(2));
                master.setSurname(resSetMas.getString(3));
                master.setTelephone(resSetMas.getString(4));
                ACC2.setMaster(master.getID(), master.getName(), master.getSurname(), master.getTelephone());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return resSetMas;
    }


    public void singUpTransportService(String last_data){
        TransportService transport_service = new TransportService();

        String insert_transport_service = "INSERT INTO " + Const.TRANSPORT_SERVICE_TABLE + "(" +
                Const.TRANSPORT_SERVICE_LAST_DATA + "," +
                Const.TRANSPORT_SERVICE_NEXT_DATA + ")" +
                "VALUE('"+last_data+"',TIMESTAMPADD(year,3,"+Const.TRANSPORT_SERVICE_LAST_DATA+"));";

        try{
            PreparedStatement prSt_Rec = getDbConnection().prepareStatement(insert_transport_service);

            prSt_Rec.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void singUpUser(Users user, Transport transport){
        String insert_user = "INSERT INTO " + Const.CLIENT_TABLE + "(" +
                Const.CLIENT_NAME + "," + Const.CLIENT_SURNAME + "," +
                Const.CLIENT_TELEPHONE + "," + Const.CLIENT_ADDRESS + ")" +
                "VALUE(?,?,?,?);";
        String insert_transport = "INSERT INTO " + Const.TRANSPORT_TABLE + "(" +
                Const.TRANSPORT_NUMBER + "," +  Const.TRANSPORT_MODEL + ","+Const.TRANSPORT_COLOR+","
                + Const.TRANSPORT_YEAR + ","+Const.TRANSPORT_SERVICE_TRANSPORT_SERVICE_ID +")" +
                " SELECT ?,?,?,?, MAX(" + Const.TRANSPORT_SERVICE_TRANSPORT_SERVICE_ID + ")+"+"1"+" FROM `" + Const.TRANSPORT_TABLE + "`;";

        try{
            PreparedStatement prSt_user = getDbConnection().prepareStatement(insert_user);
            prSt_user.setString(1, user.getName());
            prSt_user.setString(2, user.getSurname());
            prSt_user.setString(3, user.getTelephone());
            prSt_user.setString(4, user.getAddress());

            PreparedStatement prSt_transport = getDbConnection().prepareStatement(insert_transport);
            prSt_transport.setString(1, transport.getNumber());
            prSt_transport.setString(2, transport.getModel());
            prSt_transport.setString(3, transport.getColor());
            prSt_transport.setString(4, transport.getYear());

            prSt_user.executeUpdate();
            prSt_transport.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public ResultSet getUser(Users user){
        AppClientController2 ACC2 = new AppClientController2();
        ClientHomePage clientHP= new ClientHomePage();
        Transport transport = new Transport();
        Master master = new Master();
        Check check = new Check();
        TransportService transportService = new TransportService();
        DiagnosticCard digCard = new DiagnosticCard();
        ClientRecord clrec = new ClientRecord();
        ResultSet resSet  = null;

        String select = "SELECT * FROM " + Const.CLIENT_TABLE + " WHERE " +
                Const.CLIENT_NAME + "=? AND " + Const.CLIENT_SURNAME +
                "=? AND " + Const.CLIENT_TELEPHONE + "=?";


        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getName());
            prSt.setString(2, user.getSurname());
            prSt.setString(3, user.getTelephone());


            String client = "SELECT * FROM " + Const.TRANSPORT_TABLE +
                            " JOIN " + Const.CLIENT_RECORD_TABLE + " ON " + Const.TRANSPORT_ID + " = " +
                            Const.TRANSPORT_TRANSPORT_ID +
                            " JOIN " + Const.CLIENT_TABLE + " ON " + Const.CLIENT_ID + " = " + Const.CLIENT_CLIENT_ID +
                            " JOIN " + Const.MASTER_TABLE + " ON " + Const.MASTER_MASTER_ID + " = " + Const.MASTER_ID +
                            " JOIN " + Const.CHECK_TABLE + " ON " + Const.CHECK_CHECK_ID + " = " + Const.CHECK_ID +
                            " JOIN " + Const.DIAGNOSTIC_CARD_TABLE + " ON " + Const.DIAGNOSTIC_CARD_DIAGNOSTIC_CARD_ID +
                            " = " + Const.DIAGNOSTIC_CARD_ID +
                            " JOIN " + Const.TRANSPORT_SERVICE_TABLE + " ON " + Const.TRANSPORT_SERVICE_TRANSPORT_SERVICE_ID +
                            " = " + Const.TRANSPORT_SERVICE_ID +
                            " WHERE "+
                            Const.CLIENT_NAME + " = '"+user.getName()+"' AND " +
                            Const.CLIENT_SURNAME + " = '"+user.getSurname()+"' AND " +
                            Const.CLIENT_TELEPHONE + " = '"+user.getTelephone()+"';" ;


            Statement prStt = getDbConnection().createStatement();
            ResultSet resSett  = prStt.executeQuery(client);
            while(resSett.next()){
                transport.setTID(resSett.getInt(1));
                transport.setNumber(resSett.getString(2));
                transport.setModel(resSett.getString(3));
                transport.setColor(resSett.getString(4));
                transport.setYear(resSett.getString(5));
                transport.setTSID(resSett.getInt(6));

                clrec.setData(resSett.getString(8));

                master.setName(resSett.getString(22));
                master.setSurname(resSett.getString(23));
                master.setTelephone(resSett.getString(24));

                check.setEnd_data(resSett.getString(27));
                check.setPrice(resSett.getString(26));

                digCard.setComments(resSett.getString(29));

                transportService.setRecommendation(resSett.getString(32));
                transportService.setLast_data(resSett.getString(33));
                transportService.setNext_data(resSett.getString(34));

                user.setUID(resSett.getInt(16));
                user.setName(resSett.getString(17));
                user.setSurname(resSett.getString(18));
                user.setTelephone(resSett.getString(19));
                user.setAddress(resSett.getString(20));

                clientHP.getAllUser(user.getUID(), user.getName(), user.getSurname(),user.getTelephone(), user.getAddress(),
                        transport.getTID(),transport.getNumber(), transport.getModel(), transport.getColor(), transport.getYear(),
                        transport.getTSID(),master.getName(), master.getSurname(), master.getTelephone(),clrec.getData(), check.getEnd_data(),
                        check.getPrice(), digCard.getComments(), transportService.getRecommendation(), transportService.getNext_data());
                ACC2.setLastDataPicker(transportService.getLast_data());
                resSet = prSt.executeQuery();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return resSet;
    }

    public void Delete(){//Функция удаления

        String del_client = "DELETE FROM " +Const.CLIENT_TABLE+" ORDER BY " + Const.CLIENT_ID + " DESC LIMIT 1 ;";
        String del_transport = "DELETE FROM " +Const.TRANSPORT_TABLE +" ORDER BY " + Const.TRANSPORT_ID + " DESC LIMIT 1 ;";

        try{
            Statement PSdel = getDbConnection().prepareStatement(del_client);
            PSdel.execute(del_client);
            PSdel.execute(del_transport);
        }catch (SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void singUpCheck(){

        String insert_check = "INSERT INTO " + Const.CHECK_TABLE+ "(" +
                Const.CHECK_TOTAL_PRICE + "," +
                Const.CHECK_DATA+ ")" +
                "VALUE(0 ,'2000-01-01');";

        try{
            PreparedStatement prSt_Check = getDbConnection().prepareStatement(insert_check);

            prSt_Check.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void singUpDiagnosticCard(DiagnosticCard diagnosticCard){

        String insert_diagnosticCard = "INSERT INTO " + Const.DIAGNOSTIC_CARD_TABLE + "(" +
                Const.DIAGNOSTIC_CARD_COMMENTS + "," +
                Const.DC_MASTER_MASTER_ID+ ")" +
                "VALUE('Информация отсутствует' ,?);";

        try{
            PreparedStatement prSt_DC = getDbConnection().prepareStatement(insert_diagnosticCard);
            prSt_DC.setInt(1, diagnosticCard.getMID());

            prSt_DC.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void singUpClientRecord(String date, Integer UID, Integer TID, Integer TSID, Integer mID, String comment){

        String insert_clientRecord = "INSERT INTO " + Const.CLIENT_RECORD_TABLE + "(" +
                Const.CLIENT_RECORD_DATA + "," + Const.CLIENT_CLIENT_ID + "," + Const.TRANSPORT_TRANSPORT_ID + "," +
                Const.TRANSPORT_TRANSPORT_SERVICE_TRANSPORT_SERVICE_ID + "," + Const.MASTER_MASTER_ID + ","+
                Const.CHECK_CHECK_ID + "," + Const.DIAGNOSTIC_CARD_DIAGNOSTIC_CARD_ID + "," +
                Const.CLIENT_RECORD_COMMENT+ ")" +
                " SELECT '" +date+"',"+Integer.toString(UID)+","+Integer.toString(TID)+","+Integer.toString(TSID)+","+ mID+","+
                " MAX(" + Const.CHECK_CHECK_ID + ")+"+"1"+", MAX(" + Const.DIAGNOSTIC_CARD_DIAGNOSTIC_CARD_ID + ")+"+ "1"+",'"+
                comment+"' FROM `" + Const.CLIENT_RECORD_TABLE + "`;";

        try{
            PreparedStatement prSt_Rec = getDbConnection().prepareStatement(insert_clientRecord);

            prSt_Rec.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void singUpNewUser(String date, Integer mID, String comment){

        String insert_new_user = "INSERT INTO " + Const.CLIENT_RECORD_TABLE + "(" +
                Const.CLIENT_RECORD_DATA + "," + Const.CLIENT_CLIENT_ID + "," + Const.TRANSPORT_TRANSPORT_ID + "," +
                Const.TRANSPORT_TRANSPORT_SERVICE_TRANSPORT_SERVICE_ID + "," + Const.MASTER_MASTER_ID + ","+
                Const.CHECK_CHECK_ID + "," + Const.DIAGNOSTIC_CARD_DIAGNOSTIC_CARD_ID + "," +
                Const.CLIENT_RECORD_COMMENT+ ")" +
                " SELECT '" +date+"',MAX(" + Const.CLIENT_CLIENT_ID + ")+"+Integer.toString(Main.back)+",MAX("+Const.TRANSPORT_TRANSPORT_ID  + ")+"+Integer.toString(Main.back)+","+
                "MAX(" + Const.TRANSPORT_TRANSPORT_SERVICE_TRANSPORT_SERVICE_ID + ")+1,"+ mID+","+
                " MAX(" + Const.CHECK_CHECK_ID + ")+1, MAX(" + Const.DIAGNOSTIC_CARD_DIAGNOSTIC_CARD_ID + ")+1,'"+
                comment+"' FROM `" + Const.CLIENT_RECORD_TABLE + "`;";
        Main.back = 1;

        try{
            PreparedStatement prSt_Rec = getDbConnection().prepareStatement(insert_new_user);

            prSt_Rec.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
