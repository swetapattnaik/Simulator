package euiccsim;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.gigsky.raptor.model.EIS;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class MongoConnection2 {
	
	static Datastore datastore=null;
	
public static void main(String[] args) {
	
	
	checkInDb("97000000000000000000000000003201");
	
	//MongoClient 
}
public static boolean checkInDb(String eid) {
	boolean state=false;
	try {
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		DB db = mongo.getDB("EUICC");
		DBCollection table = db.getCollection("EIS");
		getDatastore(mongo);
		
		
		Query<EIS> query = datastore.createQuery(EIS.class);
		query.field("EUMSignedInfo.EID").equal(eid);
		// query.criteria("EndPointMapTable.EndpointID").equal(endpointId);
		EIS eis = query.get(); 
		if(eis!=null)
			{state=true;}
		System.out.println(eis.getSmsrId());
	
		
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return state;
}
public static Datastore getDatastore(MongoClient mongo) {
	if (datastore == null) {
	    //logger.debug(format("Starting DataStore on DB: %s", properties.getDb()));
	    datastore = new Morphia().createDatastore(mongo, "EUICC");
	}

	return datastore;
    } 
}


/*	EID" : "97000000000000000000000000003201"
	Query<EIS> query = createQuery().field("EUMSignedInfo.EID").equal(101).disableValidation();
	EIS eis = query.get();
	if (null == eis) {
		System.out.println("Unable  to fetch EIS by eid: " + 101);
	}
	return eis;
	*/