// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//


const functions = require("firebase-functions"),
  PubSub = require(`@google-cloud/pubsub`),
  admin = require("firebase-admin");

const app = admin.initializeApp();
const firestore = app.firestore();
firestore.settings({ timestampsInSnapshots: true });

const auth = app.auth();


exports.function = functions.pubsub.topic('iot-topic').onPublish(async (message) => {
    const deviceId = message.attributes.deviceId;
    console.log(message)
  
    // Write the device state into firestore
    const deviceRef = firestore.doc(`device-configs/${deviceId}`);
    try {
      // Ensure the device is also marked as 'online' when state is updated
      await deviceRef.update({ 'state': message.json, 'online': true });
      console.log(`State updated for ${deviceId}`);
    } catch (error) {
      console.error(`${deviceId} not yet registered to a user`, error);
    }
});