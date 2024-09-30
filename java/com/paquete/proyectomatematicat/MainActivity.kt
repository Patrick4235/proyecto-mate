package com.paquete.proyectomatematicat

import android.app.ActivityManager
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_ENABLE_ADMIN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hacer que la actividad sea de pantalla completa
        /*window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
             //WindowInsetsController.hide(int) with WindowInsets.Type.statusBars()
        )*/

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
            
        }


        /*val deviceAdmin = ComponentName(this, MyDeviceAdminReceiver::class.java)

        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, deviceAdmin)
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Necesitamos permisos para bloquear la pantalla")
        startActivityForResult(intent, REQUEST_CODE_ENABLE_ADMIN)*/

    }

    private fun startLockTaskMode() {
        if (isDeviceOwner()) {
            startLockTask()
            Toast.makeText(this, "Modo de Pantalla Fija Activado", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Modo de Pantalla Fija Activado", Toast.LENGTH_LONG).show()
        }
    }

    private fun isDeviceOwner(): Boolean {
        // Aquí deberías implementar la lógica que verifica si la aplicación es el propietario del dispositivo
        // En un entorno real, esto puede requerir permisos especiales o verificaciones adicionales
        return true // Cambia esto según tu lógica
    }


    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ENABLE_ADMIN) {
            if (resultCode == Activity.RESULT_OK) {
                // El usuario otorgó el permiso
                Toast.makeText(this, "Permiso de administrador otorgado", Toast.LENGTH_SHORT).show()
            } else {
                // El usuario rechazó el permiso
                Toast.makeText(this, "Permiso de administrador denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }*/

    /*fun lockDevice(view: View) {
        val devicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val componentName = ComponentName(this, MyDeviceAdminReceiver::class.java)

        if (devicePolicyManager.isAdminActive(componentName)) {
            devicePolicyManager.lockNow()  // Esto bloqueará la pantalla inmediatamente
        }
    }*/

    fun EmpezarLeccion(view: View){
        startLockTaskMode()
        startActivity(Intent(this, ExercisesActivity::class.java))
        this.finish()
    }
}