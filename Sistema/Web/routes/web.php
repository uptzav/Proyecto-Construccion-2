<?php


//Login
Route::get('/', 'AutenticacionController@Login')->name('login');
Route::post('CerrarSesion','AutenticacionController@CerrarSesion')->name('CerrarSesion');

//Autenticación
Route::post('LoginValidar', 'AutenticacionController@LoginValidar')->name('LoginValidar');

//Administracion
Route::get('CasosReportado', 'CasoReportadoController@CasosReportadoListar')->name('CasosReportado.Listar');
Route::get('Donaciones','DonacionController@DonacionListar')->name('Donacion.Listar');
Route::get('Diagnosticos','DiagnosticoController@DiagnosticosListar')->name('Diagnostico.Listar');
