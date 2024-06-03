package com.example.byteme_pft_am

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.byteme_pft_am.data.LoginDataSource
import com.example.byteme_pft_am.data.LoginRepository
import com.example.byteme_pft_am.ui.login.LoginActivity
import com.google.android.material.navigation.NavigationView


class MenuAnalistaActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_analista)


        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navegation_drawer_open, R.string.navegation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.nav_item_one -> {
                // lógica para cerrar sesión
                val loginRepository = LoginRepository(LoginDataSource())
                loginRepository.logout()

                // Redirigir a la pantalla de inicio de sesión
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish() // Finalizar la actividad actual para evitar volver atrás después del cierre de sesión

                true // Indica que se ha manejado la selección del elemento del menú
            }
            else -> false // Para otros elementos del menú, devuelve false para que la navegación se maneje de forma predeterminada
        }
    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        val loginRepository = LoginRepository(LoginDataSource())
        loginRepository.logout()

        // Redirigir a la pantalla de inicio de sesión
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Finalizar la actividad actual para evitar volver atrás después del cierre de sesión
    }
}