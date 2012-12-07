package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}
	
	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}

	//mesmo dia
	@Test
	public void deveCriarUmaSessaoQuantoDataInicioIgualDataFimEPeriodicidadeDiaria() {
		
		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate();		
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, new LocalTime(), Periodicidade.DIARIA);
		
		Assert.assertEquals( 1, sessoes.size() );		
	}
	
	//inicio maior que o fim
	@Test
	public void naoDeveCriarSessaoQuandoDataInicioMaiorQueDataFimEPeriodicidadeDiaria() {
		
		LocalDate inicio = new LocalDate().plusDays(1);
		LocalDate fim = new LocalDate();		
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, new LocalTime(), Periodicidade.DIARIA);
		
		Assert.assertEquals( 0, sessoes.size() );		
	}
	

	@Test
	public void deveCriarSessaoQuandoDataFimMaiorQueDataInicioEPeriodicidadeDiaria() {
		
		LocalDate fim = new LocalDate().plusDays(2);
		LocalDate inicio = new LocalDate();		
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, new LocalTime(), Periodicidade.DIARIA);
		
		Assert.assertEquals( 3, sessoes.size() );		
	}
	
	@Test
	public void deveCriarUmaSessaoQuandoDataInicioForIgualADataFimEPeriodicidadeSemanal() {
			
			LocalDate fim = new LocalDate();
			LocalDate inicio = new LocalDate();		
			
			Espetaculo espetaculo = new Espetaculo();
			List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, new LocalTime(), Periodicidade.SEMANAL);
			
			Assert.assertEquals( 1, sessoes.size() );		
	}

	@Test
	public void deveCriar2SessoesQuandoIntervaloMaiorQue1Semana() {
			
			LocalDate fim = new LocalDate().plusWeeks(1);
			LocalDate inicio = new LocalDate();		
			Espetaculo espetaculo = new Espetaculo();
			List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, new LocalTime(), Periodicidade.SEMANAL);
			System.out.println(sessoes.size());
			Assert.assertEquals( 2, sessoes.size() );		
	}
	
	
}
