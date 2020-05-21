package com.proyecto.androidjvapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.Executor;

public class Login extends AppCompatActivity implements View.OnClickListener {
    //defining view objects
    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnRegistrar, btnLogin, btnResetPassword;
    private ProgressDialog progressDialog;

    //Login facebook
    private CallbackManager mCallbackManager;
    private  FirebaseAuth mFirebaseAuth;
    private LoginButton loginButtonFacebook;
    private  static final String tag = "FacebookAuthentication";
    private FirebaseAuth.AuthStateListener authStateListener;
    private AccessTokenTracker accessTokenTracker;

    //Login con google
    private SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private String TAG = "GoogleAuthentication";
    private FirebaseAuth mAuthGoogle;
    private int RC_SIGN_IN = 1;

    //login huella
    private Handler handler = new Handler();

    private Executor executor = new Executor() {
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };


    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //Referenciamos los views
        TextEmail = (EditText) findViewById(R.id.EditTextEmail);
        TextPassword = (EditText) findViewById(R.id.password);

        btnRegistrar = (Button) findViewById(R.id.buttonSingup);
        btnLogin = (Button) findViewById(R.id.button_registrar);
        btnResetPassword = findViewById(R.id.button_forgot_pass);

        progressDialog = new ProgressDialog(this);

        //asociamos un oyente al evento clic del botón
        btnRegistrar.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


        //ir a recuperar contraseña
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (Login.this, forgotPass.class));
            }
        });


        //Login con facebook
        mFirebaseAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        loginButtonFacebook = findViewById(R.id.login_button);
        loginButtonFacebook.setReadPermissions("email", "public_profile");
        mCallbackManager = CallbackManager.Factory.create();
        loginButtonFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(tag,"Exito" + loginResult);
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(tag,"Cancelado" );
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(tag,"Error" + error);
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){
                    updateFB(user);
                }else{
                    updateFB(null);
                }
            }
        };

        signInButton = findViewById(R.id.login_button_google);


        //Inicializar firebase auth
        mAuthGoogle = FirebaseAuth.getInstance();


        //Configurar Google sign-in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //Sign-in action
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInGoogle();
            }
        });

        //Login Huella
        // ...
        // Prompt appears when user clicks "Log in"
        ImageView biometricLoginButton = findViewById(R.id.login_button_fingerprint);
        biometricLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBiometricPrompt();
            }
        });
    }

    //Iniciar sesión
    private void signInGoogle(){
        Intent signInGoogleIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInGoogleIntent,RC_SIGN_IN);
    }


    //Handle google
    private void handleSignInGoogleResult(Task<GoogleSignInAccount> completedTask){
        try {
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            Toast.makeText(this, "ha iniciado sesión correctamente", Toast.LENGTH_SHORT).show();
            Intent intencion = new Intent(getApplication(), CatalogoActivity.class);
            startActivity(intencion);
            FirebaseGoogleAuth(acc);
        }catch (ApiException e){
            Toast.makeText(this, "iniciar sesión sin éxito", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }

    //Sign-in google
    private void FirebaseGoogleAuth(GoogleSignInAccount acct){
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuthGoogle.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Login.this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();

                    FirebaseUser user = mAuthGoogle.getCurrentUser();
                    updateUIGoogle(user);
                }else{
                    Toast.makeText(Login.this, "Inicio de sesion fallido", Toast.LENGTH_SHORT).show();
                    updateUIGoogle(null);
                }
            }
        });
    }

    private void updateUIGoogle(FirebaseUser fUserGoogle){

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        //OnAction result Google
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task =  GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInGoogleResult(task);
        }
    }

    //Get token facebook
    private void handleFacebookToken(AccessToken token){
        Log.d(tag, "manejar token de Facebook" + token);
        AuthCredential credentialFacebook = FacebookAuthProvider.getCredential(token.getToken());
        mFirebaseAuth.signInWithCredential(credentialFacebook).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(tag, "Inicio de sesión con credencial exitoso");
                    FirebaseUser userFacebook = mFirebaseAuth.getCurrentUser();
                    Intent intencion = new Intent(getApplication(), CatalogoActivity.class);
                    startActivity(intencion);
                    updateFB(userFacebook);
                }else{
                    Log.d(tag, "Inicio de sesión con credencial fallido");
                    Toast.makeText(Login.this, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show();
                    updateFB(null);
                }
            }
        });
    }


    //update facebook
    private void updateFB(FirebaseUser user){

    }

    //on start facebook
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth .addAuthStateListener(authStateListener);
    }

    //on stop facebook


    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null){
            mFirebaseAuth.removeAuthStateListener(authStateListener);
        }
    }



    private void loguearUsuario() {
        //Obtenemos el email y la contraseña desde las cajas de texto
        final String email = TextEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email)) {//(precio.equals(""))
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Realizando consulta en linea...");
        progressDialog.show();

        //loguear usuario
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            int pos = email.indexOf("@");
                            String user = email.substring(0, pos);
                            Toast.makeText(Login.this, "Bienvenido: " + TextEmail.getText(), Toast.LENGTH_LONG).show();
                            Intent intencion = new Intent(getApplication(), CatalogoActivity.class);

                            startActivity(intencion);


                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(Login.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Login.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });


    }

    //Login huella
    private void showBiometricPrompt() {
        BiometricPrompt.PromptInfo promptInfo =
                new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Login con Huella")
                        .setSubtitle("Accede a nuestra app usando tu huella")
                        .setNegativeButtonText("Cancelar")
                        .build();

        BiometricPrompt biometricPrompt = new BiometricPrompt(Login.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                        "Error de autenticación: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                BiometricPrompt.CryptoObject authenticatedCryptoObject =
                        result.getCryptoObject();
                // User has verified the signature, cipher, or message
                // authentication code (MAC) associated with the crypto object,
                // so you can use it in your app's crypto-driven workflows.
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Autenticación fallida",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });


        // Displays the "log in" prompt.
        biometricPrompt.authenticate(promptInfo);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.buttonSingup:
                //Invocamos al método:
                Intent intencion = new Intent(getApplication(), RegistroSimple.class);
                startActivity(intencion);
                break;
            case R.id.button_registrar:
                loguearUsuario();
                break;
        }


    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
